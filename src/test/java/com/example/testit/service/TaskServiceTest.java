package com.example.testit.service;

import com.example.testit.adapter.mail.MailService;
import com.example.testit.model.Status;
import com.example.testit.model.Task;
import com.example.testit.model.User;
import com.example.testit.repository.TaskRepository;
import com.example.testit.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionErrorCollector;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TaskServiceTest {
    
    TaskRepository taskRepository;
    UserRepository userRepository;
    MailService mailService;
    TaskService taskService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        taskRepository = Mockito.mock(TaskRepository.class);
        mailService = Mockito.mock(MailService.class);

        taskService = new TaskService(taskRepository, userRepository, mailService);
    }

    @Test
    public void createTaskTest() {
        var requester = new User();
        var assigned = new User();
        var task = new Task();
        var result = new Task();



        requester.setId(1L);
        assigned.setId(2L);
        task.setRequester(requester);
        task.setAssignedUser(assigned);
        
        Mockito.when(userRepository.findById(1L)).thenReturn(
            Optional.of(requester)
        );
        Mockito.when(userRepository.findById(2L)).thenReturn(
            Optional.of(assigned)
        );
        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(task);

       result = taskService.createTask("", "", 1L,2L);
        Mockito.verify(taskRepository).save(Mockito.any(Task.class));
        Assertions.assertThat(result).isEqualTo(task);
    }

    @Test
    public void deleteTaskTest() {
        var task = new Task();
        task.setId(1L);
        taskService.deleteTask(1L);
        Mockito.verify(taskRepository).deleteById(1L);
    }

    @Test
    public void updateTaskTest() {
        var task = new Task();
        task = taskService.updateTask(task);
        Mockito.verify(taskRepository).save(Mockito.any(Task.class));
    }


    public void startTaskTest() {

    }
}

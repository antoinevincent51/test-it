@Configuration
public class SecurityConfig {
        @Bean
public SecurityFilterChain filterChain(HttpSecurity http)
        throws Exception{
        http
        .authorizeHttpRequests((authz)->authz.anyRequest().authenticated()) //On demande que toute les sessions soit authentifiée
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//On rend les session stateless
        
        return http.build();
        }

}


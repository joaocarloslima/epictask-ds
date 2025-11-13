package br.com.etecia.epictask.config;

import br.com.etecia.epictask.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain config(HttpSecurity http, UserService userService) throws Exception{
        return http
                    .authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/").permitAll()
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/tasks/**").permitAll()
                            .anyRequest().permitAll()
                    )
                    .oauth2Login(login -> login
                                            .loginPage("/login")
                                            .defaultSuccessUrl("/tasks")
                                            .userInfoEndpoint(userInfo -> userInfo.userService(userService))
                                            .permitAll()
                                        )
                    .logout(logout -> logout
                                            .logoutUrl("/logout")
                                            .logoutSuccessUrl("/login")
                                            .permitAll()
                                        )
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                    .build();

    }
    
}

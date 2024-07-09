package com.devyurakim.devschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        // Permit All Requests inside the Web Application
        http.csrf(csrf->csrf.disable()) //csrf disabling for POST, PUT requests
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/dashboard").authenticated()
                .requestMatchers("/", "home").permitAll()
                .requestMatchers("/holidays/**").permitAll()
                .requestMatchers("/contact").permitAll()
                .requestMatchers("/saveMsg").permitAll()
                .requestMatchers("/courses").permitAll()
                .requestMatchers("/about").permitAll()
                .requestMatchers("/assets/**").permitAll()
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();

        /**Deny All Requests inside the Web Application
         * 코드를 삭제하지 않고 사용자의 접근을 막아야 할 때 사용 */
        //http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll())
        //        .formLogin(Customizer.withDefaults())
        //        .httpBasic(Customizer.withDefaults());
        //return http.build();

    }

}

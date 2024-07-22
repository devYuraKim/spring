package com.devyurakim.devschool.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        // Permit All Requests inside the Web Application
        http.csrf(csrf-> csrf.ignoringRequestMatchers("/saveMsg")
                        //csrf disabled for POST, PUT, DELETE (state-changing) requests
                        .ignoringRequestMatchers("/public/**")
                        .ignoringRequestMatchers(PathRequest.toH2Console())
                        .ignoringRequestMatchers("/api/**"))
                .authorizeHttpRequests((requests) -> requests
                //actuator나 session 등 app 관련 정보는 사실 이렇게 permit all로 두면 안 됨!
                .requestMatchers("/session").permitAll()
                .requestMatchers("/api/**").authenticated()
                .requestMatchers("/dashboard").authenticated()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/displayMessages/**").hasRole("ADMIN")
                .requestMatchers("/student/**").hasRole("STUDENT")
                .requestMatchers("/displayMessages").hasRole("ADMIN")
                .requestMatchers("/closeMsg/**").hasRole("ADMIN")
                .requestMatchers("/displayProfile").authenticated()
                .requestMatchers("/updateProfile").authenticated()
                .requestMatchers("/", "home").permitAll()
                .requestMatchers("/holidays/**").permitAll()
                .requestMatchers("/contact").permitAll()
                .requestMatchers("/saveMsg").permitAll()
                .requestMatchers("/courses").permitAll()
                .requestMatchers("/about").permitAll()
                .requestMatchers("/assets/**").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/logout").permitAll()
                .requestMatchers("/public/**").permitAll()
                .requestMatchers(PathRequest.toH2Console()).permitAll() //H2 Console은 이렇게 해야 한다
                )
                .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
                        .defaultSuccessUrl("/dashboard")
                        .failureUrl("/login?error=true")
                        .permitAll())
                //.logout(logoutConfigurer -> logoutConfigurer
                //        .logoutSuccessUrl("/login?logout=true")
                //        .invalidateHttpSession(true).permitAll()) //csrf enabled -> login controller의 logout logic이 제대로 invoke 되지 않음
                .httpBasic(Customizer.withDefaults());

        http.headers(headersConfigurer -> headersConfigurer
                .frameOptions(frameOptionsConfig -> frameOptionsConfig.disable())); //h2 console 보기 위한 frame 설정 끄는 내용

        return http.build();

        /**Deny All Requests inside the Web Application
         * 코드를 삭제하지 않고 사용자의 접근을 막아야 할 때 사용
         * http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build(); */

    }

    /*The BCryptPasswordEncoder automatically generates a random salt each time you encode a password,
    and this salt is included in the resulting hash.

    PasswordEncoder class로 지정하면 암호화 알고리즘을 바꿔도 다른 곳에서 코드 변경할 필요 없음!
    */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**inMemoryAuthentication()
     * UserDetails 객체 생성 후, InMemoryUserDetailsManager에 변수로 전달하면 끝
    @Bean
    InMemoryUserDetailsManager userDetailsService(){
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("12345")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    } */
}

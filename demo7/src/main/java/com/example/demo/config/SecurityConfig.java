package com.example.demo.config;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpSession;

@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("user")
//                .password(passwordEncoder().encode("1234"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("---------filterChain-------------");
        http.authorizeHttpRequests()
//                .antMatchers("스웨거나 docs 관련 url","css, js 관련 url").permitAll()
                .antMatchers("/sample/admin").hasRole("ADMIN")
                .antMatchers("/sample/all").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated();
//        http.formLogin()
//                .loginPage("/login") // 커스텀 로그인 페이지 URL
//                .loginProcessingUrl("/perform_login") // 로그인 form action URL
//                .defaultSuccessUrl("/home", true) // 로그인 성공 시 이동할 URL
//                .failureUrl("/login?error=true") // 로그인 실패 시 이동할 URL
//                .permitAll();
        http.formLogin();
        http.logout();
        http.exceptionHandling()
                .accessDeniedPage("/sample/accessDenied");
        http.csrf().disable();
        http.csrf()
                .ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin();  // 여기!
        return http.build();
    }


}

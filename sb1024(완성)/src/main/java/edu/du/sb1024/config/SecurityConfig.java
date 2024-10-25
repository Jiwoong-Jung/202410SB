package edu.du.sb1024.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
// @EnableGlobalMethodSecurity(    // Method 단위 시큐리티 처리.
//        prePostEnabled = true,  // Spring Security의 @PreAuthorize, @PreFilter /@PostAuthorize, @PostFilter어노테이션 활성화 여부
//        securedEnabled = true,  // @Secured어노테이션 활성화 여부
//        jsr250Enabled = true)   // @RoleAllowed 어노테이션 사용 활성화 여부
@Log4j2
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("user1")
//                .password(passwordEncoder().encode("1234"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("---------filterChain-------------");
        http.authorizeHttpRequests()
//                .antMatchers("/**").denyAll()
//                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/sample/all").permitAll()
                .antMatchers("/register/**").permitAll()
                .antMatchers("/board/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/sample/admin").hasRole("ADMIN")
                .anyRequest().authenticated();

//        http.formLogin();
//        http.formLogin().loginPage("/sample/login").permitAll();
//        http.csrf().disable();
//        http.logout();
        http.formLogin()
                .loginPage("/sample/login") // 로그인 페이지 URL 설정
                .defaultSuccessUrl("/sample/all", true) // 로그인 성공 후 리다이렉트 URL 설정
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/sample/logout") // 로그아웃 URL 설정
                .logoutSuccessUrl("/sample/login") // 로그아웃 성공 후 리다이렉트 URL 설정
                .invalidateHttpSession(true) // 로그아웃 시 세션 무효화
                .deleteCookies("JSESSIONID") // 로그아웃 시 쿠키 삭제
                .permitAll();
        http
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()); // CSRF 토큰 설정

        http.exceptionHandling().accessDeniedPage("/sample/accessDenied");
//        http.csrf()
//                .ignoringAntMatchers("/h2-console/**")
//                .and().headers().frameOptions().sameOrigin();  // 여기!

        return http.build();
    }
}

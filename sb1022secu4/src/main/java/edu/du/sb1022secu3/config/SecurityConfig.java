package edu.du.sb1022secu3.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpSession;

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
//        UserDetails user = User.withUsername("user123")
//                .password(passwordEncoder().encode("1234"))
//                .roles("ADMIN")
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
                .antMatchers("/login").permitAll()
                .antMatchers("/sample/admin").hasRole("ADMIN")
                .anyRequest().authenticated();

        http.formLogin();
//        http.formLogin().loginPage("/sample/login").permitAll();
        http.csrf().disable();
// 로그아웃 설정
        http.logout(logout -> logout
                // 로그아웃 요청을 처리할 URL 설정
                .logoutUrl("/logout")
                // 로그아웃 성공 시 리다이렉트할 URL 설정
                .logoutSuccessUrl("/login")
                // 로그아웃 핸들러 추가 (세션 무효화 처리)
                .addLogoutHandler((request, response, authentication) -> {
                    HttpSession session = request.getSession();
                    session.invalidate();
                })
                // 로그아웃 성공 핸들러 추가 (리다이렉션 처리)
                .logoutSuccessHandler((request, response, authentication) ->
                        response.sendRedirect("/login"))
                // 로그아웃 시 쿠키 삭제 설정 (예: "remember-me" 쿠키 삭제)
                .deleteCookies("remember-me")

        );

        http.exceptionHandling().accessDeniedPage("/sample/accessDenied");
        http.csrf()
                .ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin();  // 여기!

        return http.build();
    }

    // AuthenticationManager 객체 빈 주입
    // AuthenticationManager 는 스프링 시큐리티의 인증을 담당
//    @Bean
//    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
}

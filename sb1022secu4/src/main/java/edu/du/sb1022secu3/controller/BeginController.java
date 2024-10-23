package edu.du.sb1022secu3.controller;

import edu.du.sb1022secu3.entity.Member;
import edu.du.sb1022secu3.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;

@Controller
@Log4j2
public class BeginController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/")
    public String index() {
        return "/sample/all";
    }

//    @GetMapping("/login")
//    public String loginForm() {
//        return "/sample/loginForm";
//    }
//
//    @PostMapping("/login")
//    public String loginResult(@RequestParam String username, @RequestParam String password, Model model) {
//        log.info("이메일 {}, 패스워드 {}", username, password);
//        return "/";
//    }

    @PostConstruct
    public void init() {
        Member member = Member.builder()
                .id(1001L)
                .username("hong1")
                .password(passwordEncoder().encode("1234"))
                .email("hong1@aaa.com")
                .role("ADMIN")
                .build();
        memberRepository.save(member);

        member = Member.builder()
                .id(1002L)
                .username("test1")
                .password(passwordEncoder().encode("1234"))
                .email("test1@aaa.com")
                .role("USER")
                .build();
        memberRepository.save(member);
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

package com.example.demo.service;

import com.example.demo.dto.ClubAuthMemberDTO;
import com.example.demo.entity.Member;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("==========>사용자: " + username);
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("1234"))
                .roles("USER")
                .build();
//        ClubAuthMemberDTO user = ClubAuthMemberDTO.builder()
//                .username(user1.getEmail())
//                .password(member.getPassword())
//                .authorities(new SimpleGrantedAuthority(member.getRole().toString()))
//                .build();

        Member member = Member.builder()
                .id(1001L)
                .username("홍길동")
                .password(passwordEncoder().encode("1234"))
                .email("hong@aaa.com")
                .build();
        return toUserDetails(member);
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    private UserDetails toUserDetails(Member member) {
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
//                .authorities(new SimpleGrantedAuthority(member.getRole().toString()))
                .roles("USER")
                .build();
    }
}

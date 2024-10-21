package edu.du.sb1021_2;

import edu.du.sb1021_2.entity.Member;
import edu.du.sb1021_2.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Date;

@SpringBootTest
class Sb10212ApplicationTests {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void 입력_테스트() {
        Member member = Member.builder()
                .name("홍길동")
                .password("1234")
                .regdate(new Date())
                .build();
        System.out.println(memberRepository.save(member));
    }

}

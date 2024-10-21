package edu.du.sb1021_2;

import edu.du.sb1021_2.entity.Member;
import edu.du.sb1021_2.repository.MemberRepository;
import edu.du.sb1021_2.spring.MemberDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SpringBootTest
class Sb10212ApplicationTests {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberDao memberDao;

    @Test
    void 입력_테스트() {
        Member member = Member.builder()
                .name("홍길동")
                .password("1234")
                .email("hong@korea.com")
                .regdate(LocalDateTime.now())
                .build();
        System.out.println(memberRepository.save(member));

        Member members = memberDao.selectByEmail("hong@korea.com");
        System.out.println(members);
    }

}

package edu.du.sb1023;

import edu.du.sb1023.entity.Member;
import edu.du.sb1023.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

@SpringBootTest
class Sb1023ApplicationTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    void test_save() {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        // em.persist() 사용하여 데이터를 입력해 보세요.

        transaction.commit();
    }



    @Test
    void test_순수객체() {
        Member member1 = new Member("member1", "회원1");
        Member member2 = new Member("member1", "회원1");
        Team team = new Team("team1", "팀1");

        member1.setTeam(team);
        member2.setTeam(team);
        System.out.println(member1);
        System.out.println(member2);

    }

}

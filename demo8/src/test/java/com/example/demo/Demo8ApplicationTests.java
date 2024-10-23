package com.example.demo;

import com.example.demo.entity.Member;
import com.example.demo.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.*;
import java.util.List;

@SpringBootTest
class Demo8ApplicationTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager em;

    @Test
    void test1() {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        // 팀1 저장
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        // 회원1 저장
        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);  // 연관관계 설정 member1 -> team1
        em.persist(member1);

        // 회원1 저장
        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);  // 연관관계 설정 member1 -> team1
        em.persist(member2);

        transaction.commit();   // DBMS에 update가 발생함


    }

    @Test
    void test2() {
        //        검색
        String jpql = "select m from Member m join m.team t where t.name=:teamName";

        List<Member> resultList = em.createQuery(jpql, Member.class)
                .setParameter("teamName", "팀1")
                .getResultList();

        resultList.forEach(m -> System.out.println("[query] member.username=" + m.getUsername()));
    }

    @Test
    void test3() {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        // 새로운 팀2
        Team team2 = new Team("team2", "팀2");
        em.persist(team2);

        // 회원에 새로운 팀2 설정
        em.find(Member.class, "member1").setTeam(team2);

        transaction.commit();   // DBMS에 update가 발생함
    }

    @Test
    void test4() {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Member member1 = em.find(Member.class, "member1");
        member1.setTeam(null);

        transaction.commit();   // DBMS에 update가 발생함
    }

    @Test
    void test5() {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Team team = em.find(Team.class, "team1");
        team.getMembers().forEach(t -> System.out.println("member.username=" + t.getUsername()));
        transaction.commit();   // DBMS에 update가 발생함
    }

    @Test
    void test6() {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        // 회원1 저장
        Member member1 = new Member("member1", "회원1");
        em.persist(member1);

        // 회원1 저장
        Member member2 = new Member("member2", "회원2");
        em.persist(member2);

        Team team1 = new Team("team1", "팀1");
        // 주인이 아닌 곳에만 연관관계 설정
        team1.getMembers().add(member1);
        team1.getMembers().add(member2);

        em.persist(team1);

        transaction.commit();   // DBMS에 update가 발생함
    }

    @Test
    void test7() {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        // 팀1 저장
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");

        member1.setTeam(team1);          // 연관관계 설정 member1 -> team1
        team1.getMembers().add(member1); // 연관관계 설정 team1 -> member1
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");

        member2.setTeam(team1);          // 연관관계 설정 member2 -> team1
        team1.getMembers().add(member2); // 연관관계 설정 team1 -> member2
        em.persist(member2);

        List<Member> members = team1.getMembers();
        System.out.println("members.size = " + members.size());

        transaction.commit();   // DBMS에 update가 발생함
    }

}

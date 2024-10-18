package edu.du.sb1018;

import edu.du.sb1018.entity.Dept;
import edu.du.sb1018.entity.Emp;
import edu.du.sb1018.service.EmService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.*;
import java.util.List;

@SpringBootTest
class Sb1018ApplicationTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    EmService emService;

    @Test
    void 영속성_find_테스트() {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Dept dept = em.find(Dept.class, 10);  // find = select
        dept.setDname("서울");
        System.out.println(dept);
        transaction.commit();   // DBMS에 update가 발생함
    }

    @Test
    void 영속성_merge_테스트() {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Dept dept = em.find(Dept.class, 10);  // find = select
        dept.setDname("성남");
        System.out.println(dept);
        em.detach(dept);  // 영속성을 잃음
        em.merge(dept);  // merge = update
        transaction.commit();   // DBMS에 update가 발생함
    }

    @Test
    void 영속성_persist_테스트() {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Dept 엔티티 객체 생성
        Dept newDept = new Dept();
        newDept.setDeptno(50);
        newDept.setDname("연구소");
        newDept.setLoc("서울");

        // 엔티티를 데이터베이스에 저장
        em.persist(newDept);  // persist = insert

        // 트랜잭션 커밋
        em.getTransaction().commit();
    }
    @Test
    void 영속성_remove_테스트() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Dept dept = em.find(Dept.class, 50);
        System.out.println(dept);
        em.remove(dept);   // remove = delete
        transaction.commit();
    }

    @Test
    void update_테스트() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Dept dept = em.find(Dept.class, 10);
        dept.setDname("Accounting");
        dept.setLoc("Seoul");
        System.out.println(dept);
        transaction.commit();
    }

    @Test
    void createQuery_테스트() {
        Dept dept = em.find(Dept.class, 10);
        System.out.println(dept);
        System.out.println("===========================================");
        TypedQuery<Dept> query = em.createQuery("SELECT d FROM Dept d", Dept.class);
        List<Dept> depts = query.getResultList();
        for (Dept d : depts) {
            System.out.println(d);
        }
        System.out.println("===========================================");
        TypedQuery<Emp> query2 = em.createQuery("SELECT e FROM Emp e", Emp.class);
        List<Emp> empList = query2.getResultList();
        for (Emp emp : empList) {
            System.out.println(emp);
        }
    }
    @Test
    void createQuery_영속성테스트() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        System.out.println("===========================================");
        TypedQuery<Dept> query = em.createQuery("SELECT d FROM Dept d", Dept.class);
        List<Dept> depts = query.getResultList();
        for (Dept d : depts) {
            System.out.println(d);
        }
        System.out.println(depts.get(0));
        depts.get(0).setLoc("인천");
        transaction.commit();
    }
    @Test
    void createQuery_테스트2() {
        String jpql = "SELECT d FROM Dept d WHERE d.dname = :aaa";
        TypedQuery<Dept> query = em.createQuery(jpql, Dept.class);
        query.setParameter("aaa", "Accounting");
        List<Dept> depts = query.getResultList();
        Dept dept = depts.get(0);
        System.out.println(dept);
        System.out.println("===========================================");
        String jpql2 = "SELECT d FROM Emp d WHERE d.deptno = :deptNo";
        TypedQuery<Emp> query2 = em.createQuery(jpql2, Emp.class);
        query2.setParameter("deptNo", dept.getDeptno());
        List<Emp> empList = query2.getResultList();
        for (Emp emp : empList) {
            System.out.println(emp);
        }
    }

    @Test
    void EmService_테스트() {
        List<Emp> list = emService.findWithName("%K%");
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }

}

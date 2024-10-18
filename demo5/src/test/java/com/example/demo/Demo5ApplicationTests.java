package com.example.demo;

import com.example.demo.entity.Dept;
import com.example.demo.entity.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@SpringBootTest
class Demo5ApplicationTests {

    @PersistenceContext
//    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    EntityManager em;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Test
    void testEm4() {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Dept dept = em.find(Dept.class, 2);
        System.out.println(dept);
        em.remove(dept);
        transaction.commit();
    }

    @Test
    @Transactional
    void testEm2() {
        // 트랜잭션 시작
//        em.getTransaction().begin();

        // Dept 엔티티 객체 생성
        Dept newDept = new Dept();
        newDept.setDeptno(60);
        newDept.setDname("Research2");
        newDept.setLoc("연구소2");

        // 엔티티를 데이터베이스에 저장
        em.persist(newDept);

        // 트랜잭션 커밋
//        em.getTransaction().commit();

        // 저장된 객체를 다시 조회
        Dept savedDept = em.find(Dept.class, 60);
        System.out.println("Saved Dept: " + savedDept.getDname());
    }

    @Test
    void testEM() {
//        em.getTransaction().begin();
        Dept dept = em.find(Dept.class, 10);
        System.out.println(dept);
        TypedQuery<Dept> query = em.createQuery("SELECT d FROM Dept d", Dept.class);
        List<Dept> depts = query.getResultList();
        System.out.println(depts);
//        em.getTransaction().commit();
        System.out.println("===========================================");
        String jpql = "SELECT d FROM Dept d WHERE d.dname = :deptName";
        TypedQuery<Dept> query2 = em.createQuery(jpql, Dept.class);
        query2.setParameter("deptName", "Accounting");
        List<Dept> deptList = query2.getResultList();
        Dept dept1 = deptList.get(0);
        System.out.println(dept1.getDeptno());
        System.out.println("===========================================");
        String jpql3 = "SELECT d FROM Emp d WHERE d.deptno = :deptNo";
        TypedQuery<Emp> query3 = em.createQuery(jpql3, Emp.class);
        query3.setParameter("deptNo", dept1.getDeptno());
        List<Emp> empList = query3.getResultList();
        for (Emp emp : empList) {
            System.out.println(emp);
        }
    }

    @Test
    void testEm3() {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        // Dept 엔티티 객체 생성
        Dept newDept = new Dept();
        newDept.setDeptno(60);
        newDept.setDname("연구소3");
        newDept.setLoc("서울");

        // 엔티티를 데이터베이스에 저장
        em.persist(newDept);

        // 트랜잭션 커밋
        transaction.commit();

        // 저장된 객체를 다시 조회
        Dept savedDept = em.find(Dept.class, newDept.getDeptno());
        System.out.println("Saved Dept: " + savedDept.getDname() + ", Location: " + savedDept.getLoc());
    }

    @Test
    void testUpdateDept() {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();
        // 트랜잭션 시작
        em.getTransaction().begin();

        // 업데이트할 Dept 엔티티 조회
        Dept deptToUpdate = em.find(Dept.class, 60); // 예시로 ID가 1인 Dept 엔티티를 조회

        if (deptToUpdate != null) {
            // 엔티티의 필드 수정
            deptToUpdate.setDname("Updated Name");
            deptToUpdate.setLoc("Updated Location");

            // 엔티티를 병합하여 업데이트
            em.merge(deptToUpdate);
        }

        // 트랜잭션 커밋
        em.getTransaction().commit();

        // 업데이트된 객체를 다시 조회
        Dept updatedDept = em.find(Dept.class, 60);
        System.out.println("Updated Dept: " + updatedDept.getDname() + ", Location: " + updatedDept.getLoc());
    }

    @Test
    public void main(String[] args) throws IOException {
//        ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("명령어를 입력하세요:");
            String command = reader.readLine();
            if (command.equalsIgnoreCase("exit")) {
                System.out.println("종료합니다.");
                break;
            }
            if (command.startsWith("new ")) {
                processNewCommand(command.split(" "));
            } else if (command.startsWith("change ")) {
                processChangeCommand(command.split(" "));
            } else if (command.equals("list")) {
                processListCommand();
            } else if (command.startsWith("info ")) {
                processInfoCommand(command.split(" "));
            } else {
                printHelp();
            }
        }
//        ctx.close();
    }

    private static void processNewCommand(String[] arg) {
        if (arg.length != 5) {
            printHelp();
            return;
        }
//        MemberRegisterService regSvc =
//                ctx.getBean("memberRegSvc", MemberRegisterService.class);
//        RegisterRequest req = new RegisterRequest();
//        req.setEmail(arg[1]);
//        req.setName(arg[2]);
//        req.setPassword(arg[3]);
//        req.setConfirmPassword(arg[4]);
//
//        if (!req.isPasswordEqualToConfirmPassword()) {
//            System.out.println("암호와 확인이 일치하지 않습니다.\n");
//            return;
//        }
//        try {
//            regSvc.regist(req);
//            System.out.println("등록했습니다.\n");
//        } catch (DuplicateMemberException e) {
//            System.out.println("이미 존재하는 이메일입니다.\n");
//        }
    }

    private static void processChangeCommand(String[] arg) {
        if (arg.length != 4) {
            printHelp();
            return;
        }
//        ChangePasswordService changePwdSvc =
//                ctx.getBean("changePwdSvc", ChangePasswordService.class);
//        try {
//            changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
//            System.out.println("암호를 변경했습니다.\n");
//        } catch (MemberNotFoundException e) {
//            System.out.println("존재하지 않는 이메일입니다.\n");
//        } catch (WrongIdPasswordException e) {
//            System.out.println("이메일과 암호가 일치하지 않습니다.\n");
//        }
    }

    private static void processListCommand() {
//        MemberListPrinter listPrinter =
//                ctx.getBean("listPrinter", MemberListPrinter.class);
//        listPrinter.printAll();
    }

    private static void processInfoCommand(String[] arg) {
        if (arg.length != 2) {
            printHelp();
            return;
        }
//        MemberInfoPrinter infoPrinter =
//                ctx.getBean("infoPrinter", MemberInfoPrinter.class);
//        infoPrinter.printMemberInfo(arg[1]);
    }

    private static void printHelp() {
        System.out.println();
        System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
        System.out.println("명령어 사용법:");
        System.out.println("new 이메일 이름 암호 암호확인");
        System.out.println("change 이메일 현재비번 변경비번");
        System.out.println("info 이메일");

        System.out.println();
    }
}

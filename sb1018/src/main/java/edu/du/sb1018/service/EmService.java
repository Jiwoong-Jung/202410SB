package edu.du.sb1018.service;

import edu.du.sb1018.entity.Dept;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Service
@Slf4j
public class EmService {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager em;

    public Dept updateDept(int deptNo, String deptName) {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Dept dept = em.find(Dept.class, deptNo);  // find = select
        if (dept != null) {
            dept.setDname(deptName);
            log.info("update dept {} with name {}", deptNo, deptName);
        } else {
            log.info("해당 {} 부서가 없습니다.", deptNo);
        }
        transaction.commit();   // DBMS에 update가 발생함
        return dept;
    }

    public List findWithName(String name) {
        return em.createQuery(
                        "SELECT c FROM Emp c WHERE c.ename LIKE :custName")
                .setParameter("custName", name)
                .setMaxResults(10)
                .getResultList();
    }
}

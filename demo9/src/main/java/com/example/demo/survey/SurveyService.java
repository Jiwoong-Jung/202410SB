package com.example.demo.survey;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

@Service
@Log4j2
public class SurveyService {
    @PersistenceUnit
    private EntityManagerFactory emf;

    public void save(AnsweredData data) {
        // 트랜잭션 시작
        EntityManager em = emf.createEntityManager();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        log.info(data.toString());
        Respondent respondent = data.getRes();
        em.persist(respondent);
        em.persist(data);
        transaction.commit();
    }
}

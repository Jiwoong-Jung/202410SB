package com.study.emjpa.entity;

import com.study.emjpa.repository.MyEntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyEntityManagerTest {

    @Autowired
    MyEntityManager myem;
    @Test
    public void test1() {
        myem.personCreate();
    }
}
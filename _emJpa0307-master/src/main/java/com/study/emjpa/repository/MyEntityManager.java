package com.study.emjpa.repository;

import com.study.emjpa.entity.Cart;
import com.study.emjpa.entity.Person;
import com.study.emjpa.entity.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.IntStream;

@Repository
@Log4j2
public class MyEntityManager {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public Person personCreate() {
        log.info("------->"+em.getProperties());
        Person person = Person.builder().name("홍").addr("마포").build();
        em.persist(person);
        log.info("------->"+person);
        return person;
    }

    @Transactional
    public String create() {

        IntStream.rangeClosed(1, 10).forEach(t->{
            Person person = Person.builder().name("김"+t).addr("서대문"+t).build();
            em.persist(person);
            log.info("------->"+t+":"+person);
        });

        return "입력";
    }

    @Transactional
    public String update() {
        Person person = em.find(Person.class, 1L);
        log.info("----------->"+person);
        person.setAddr("성남");
        em.merge(person);
        return "수정";
    }

    @Transactional
    public String delete() {
        Person person = em.find(Person.class, 1L);
        log.info("----------->"+person);
        em.remove(person);
        return "삭제";
    }

    public List<Person> selectAll() {
        String sql = "select p from Person p";
        List<Person> list = em.createQuery(sql, Person.class).getResultList();
        return  list;
    }

    @Transactional
    public String putInCart() {

        Cart cart = Cart.builder().build();
        em.persist(cart);
        log.info("===>카트생성?"+em.contains(cart));
        IntStream.rangeClosed(1, 5).forEach(t->{
            Product product = Product.builder().cart(cart).name("물건"+t).price(2000+t*10).build();
            em.persist(product);
            log.info("===>물건넣기?"+em.contains(product));
        });

        return "생성";
    }

    public Cart getCart() {
        Cart cart = em.find(Cart.class, 1L);
        return cart;
    }

    public Cart getCart2(Long id) {
        Cart cart = em.find(Cart.class, id);
        return cart;
    }
}

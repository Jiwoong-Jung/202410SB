package com.study.emjpa.repository;

import com.study.emjpa.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByOrderByIdDesc();
}

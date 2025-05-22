package com.basic.myspringboot.repository;

import com.basic.myspringboot.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //Query Method 정의하면 JPA가 JPQL(java persistence query language)
    Optional<Customer> findByCustomerId(String id); // 메소드 명이 중요하다. findbyCustomerId 라고 하면 오류 발생
    List<Customer> findByCutomerName(String name);


}

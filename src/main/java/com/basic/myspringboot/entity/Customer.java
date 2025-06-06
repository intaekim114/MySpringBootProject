package com.basic.myspringboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "customers")
@Getter @Setter
@DynamicUpdate
public class Customer {
    // Primary Key, pk 값을 persistence provider가 결정해라
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 중복 허용하지 않고, Null 값을 허용하지 않는다.
    @Column(unique = true, nullable = false) // 중복 불가, null값 불가
    private String customerId;

    @Column(nullable = false)
    private String customerName;

}

package com.productmanager.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private boolean isActive;

    @CreationTimestamp
    @Column (updatable = false)
    private LocalDateTime createdAt;


    @UpdateTimestamp
     private LocalDateTime updatedAt;




}

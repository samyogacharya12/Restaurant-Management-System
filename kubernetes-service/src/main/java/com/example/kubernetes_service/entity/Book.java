package com.example.kubernetes_service.entity;


import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String isbn;
}

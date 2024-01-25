package com.example.ApiIntegration.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table
@RequiredArgsConstructor
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "create_date")
    private final LocalDateTime createDate = LocalDateTime.now().withNano(0);

}

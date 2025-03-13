package com.example.blog.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity  
@Getter @Setter 
@NoArgsConstructor 
@AllArgsConstructor 
public class Blog {
    
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    
    private Long id;  

    private String title; 
    
    @Column(columnDefinition = "TEXT")  
    
    private String content;  

    private String author; 
    private LocalDateTime createdAt = LocalDateTime.now();  
    
}

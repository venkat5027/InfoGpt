package com.example.InfoGpt.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="Faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int age;
    private boolean gender;
    private float experience;
    private String programmingLanguage;
    private String organization;
}

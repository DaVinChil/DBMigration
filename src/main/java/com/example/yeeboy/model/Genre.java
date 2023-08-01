package com.example.yeeboy.model;

import jakarta.persistence.*;

@Entity
@Table(name = "genre", schema = "CinemaExpert")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
}

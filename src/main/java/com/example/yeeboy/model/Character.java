package com.example.yeeboy.model;

import jakarta.persistence.*;

@Entity
@Table(name = "character", schema = "CinemaExpert")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
}

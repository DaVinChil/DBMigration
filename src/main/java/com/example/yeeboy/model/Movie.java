package com.example.yeeboy.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "movie", schema = "CinemaExpert")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @ManyToMany(mappedBy = "filmography")
    private List<Person> actors;
    private String description;
    private long runningTime;
    private String title;
    private double chartRating;
    private long year;
    @OneToMany
    private List<Genre> genre;
    @OneToOne
    private Image image;
}

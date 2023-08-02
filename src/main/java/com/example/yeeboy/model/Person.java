package com.example.yeeboy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "person", schema = "CinemaExpert")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String birthData;
    private String birthPlace;
    private String deathCause;
    private String deathPlace;
    @ManyToMany
    @JoinTable(name = "Person_Movie", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private ArrayList<Movie> filmography;
    private String fullName;
    private String gender;
    private double height;
    @OneToOne
    private Image photo;
}

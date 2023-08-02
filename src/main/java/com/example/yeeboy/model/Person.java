package com.example.yeeboy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "height")
    private double height;
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "death_date")
    @Temporal(TemporalType.DATE)
    private Date deathDate;

    @Column(name = "death_cause")
    private String deathCause;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "photo_id", referencedColumnName = "image_id")
    private Image photo;

    @OneToMany(mappedBy = "actor", cascade = CascadeType.PERSIST)
    private List<Character> characters;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> filmography;
}

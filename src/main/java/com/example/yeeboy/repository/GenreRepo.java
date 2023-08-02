package com.example.yeeboy.repository;

import com.example.yeeboy.data_model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepo extends JpaRepository<Genre, Long> {
    boolean existsByName(String name);
    Genre findByName(String name);
}

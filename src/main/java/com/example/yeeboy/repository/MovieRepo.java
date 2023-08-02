package com.example.yeeboy.repository;

import com.example.yeeboy.data_model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long> {
    Movie findByImdbId(String imdbId);
    boolean existsByImdbId(String imdbId);
}

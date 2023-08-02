package com.example.yeeboy.repository;

import com.example.yeeboy.data_model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Long> {
    boolean existsByUrl(String url);
    Image findByUrl(String url);
}

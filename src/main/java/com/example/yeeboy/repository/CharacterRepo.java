package com.example.yeeboy.repository;

import com.example.yeeboy.data_model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepo extends JpaRepository<Character, Long> {
    boolean existsByActor_IdAndMovie_IdAndName(long actorId, long movieId, String name);

}

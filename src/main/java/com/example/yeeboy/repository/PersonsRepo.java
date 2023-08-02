package com.example.yeeboy.repository;

import com.example.yeeboy.data_model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonsRepo extends JpaRepository<Person, Long> {

}

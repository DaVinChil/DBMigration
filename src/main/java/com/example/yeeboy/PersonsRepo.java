package com.example.yeeboy;

import com.example.yeeboy.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonsRepo extends JpaRepository<Person, Long> {

}

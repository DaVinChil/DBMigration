package com.example.yeeboy;

import com.example.yeeboy.model.Movie;
import com.example.yeeboy.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class Controller {
    private final PersonsRepo persons;
    private final MovieRepo movies;

//    @PostMapping("/person")
//    public ResponseEntity<Void> postPerson(@RequestBody List<Person> person) {
//
//    }
//
//    @PostMapping("/movie")
//    public ResponseEntity<Void> postMovie(@RequestBody Movie movie){
//
//    }
}

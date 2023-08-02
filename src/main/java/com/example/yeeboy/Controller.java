package com.example.yeeboy;

import com.example.yeeboy.data_model.Genre;
import com.example.yeeboy.data_model.Movie;
import com.example.yeeboy.data_model.Person;
import com.example.yeeboy.dto.DTOMovie;
import com.example.yeeboy.dto.DTOPerson;
import com.example.yeeboy.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class Controller {
    private final PersonsRepo persons;
    private final MovieRepo movies;
    private final ImageRepo images;
    private final GenreRepo genres;
    private final CharacterRepo characters;

    @PostMapping("/person")
    public ResponseEntity<Void> postPerson(@RequestBody List<DTOPerson> dtoPerson) {

    }

    @PostMapping("/movie")
    public ResponseEntity<Void> postMovie(@RequestBody DTOMovie dtoMovie){

    }

    public Person convertDto(DTOPerson dtoPerson){
        Person person = new Person();
        person.setFullName(dtoPerson.getFullName());
        person.setBirthday(Date.valueOf(dtoPerson.getBirthDate()));
        person.setBirthPlace(dtoPerson.getBirthPlace());
        person.setDeathCause(dtoPerson.getDeathCause());
        person.setDeathDate(Date.valueOf(dtoPerson.getDeathDate()));
        person.setDeathPlace(dtoPerson.getDeathPlace());
        person.setHeight(dtoPerson.getHeight());
        person.setGender(dtoPerson.getGender());
        persons.save(person);
    }

    public Movie covertDto(DTOMovie dtoMovie){
        Movie movie = new Movie();
        movie.setTitle(dtoMovie.getTitle());
        movie.setChartRating(dtoMovie.getChartRating());
        for(String genreName : dtoMovie.getGenre()) {
            if(genres.existsByName(genreName)){
                Genre genre = genres.findByName(genreName);
                movie.addGenre(genre);
            } else {
                Genre genre = new Genre();
                genre.setName(genreName);
                movie.addGenre(genre);
            }
        }
        movie.setYear((int) dtoMovie.getYear());
        movie.setRunningTimeInMinutes((int) dtoMovie.getRunningTimeInMinutes());

    }
}

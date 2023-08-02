package com.example.yeeboy;

import com.example.yeeboy.data_model.Character;
import com.example.yeeboy.data_model.Genre;
import com.example.yeeboy.data_model.Image;
import com.example.yeeboy.data_model.Movie;
import com.example.yeeboy.data_model.Person;
import com.example.yeeboy.dto.DTOMovie;
import com.example.yeeboy.dto.DTOPerson;
import com.example.yeeboy.repository.*;
import lombok.RequiredArgsConstructor;
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
    public void postPerson(@RequestBody List<DTOPerson> dtoPersons) {
        for(DTOPerson person : dtoPersons){
            Person pers = convertDto(person);
            persons.save(pers);
        }
    }

    @PostMapping("/movie")
    public void postMovie(@RequestBody List<DTOMovie> dtoMovies){
        for(DTOMovie dtoMovie : dtoMovies) {
            Movie movie = convertDto(dtoMovie);
            movies.save(movie);
        }
    }

    public Person convertDto(DTOPerson dtoPerson){
        Person person = new Person();
        person.setFullName(dtoPerson.getFullName());
        if(dtoPerson.getBirthDate() != null){
            person.setBirthday(Date.valueOf(dtoPerson.getBirthDate()));
        }
        if(dtoPerson.getBirthPlace() != null) {
            person.setBirthPlace(dtoPerson.getBirthPlace());
        }
        if(dtoPerson.getDeathCause() != null) {
            person.setDeathCause(dtoPerson.getDeathCause());
        }
        if(dtoPerson.getDeathDate() != null){
            person.setDeathDate(Date.valueOf(dtoPerson.getDeathDate()));
        }
        if(dtoPerson.getDeathPlace() != null) {
            person.setDeathPlace(dtoPerson.getDeathPlace());
        }
        person.setHeight(dtoPerson.getHeight());
        if(dtoPerson.getGender() != null){
            person.setGender(dtoPerson.getGender());
        }
        return person;
    }

    public Movie convertDto(DTOMovie dtoMovie){
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

        Image image = new Image();
        image.setHeight(image.getHeight());
        image.setWidth(image.getWidth());
        image.setUrl(image.getUrl());
        images.save(image);

        for(DTOMovie.Actor actor : dtoMovie.getActors()) {
            Person person = persons.findByImdbId(actor.getId());
            movie.addActor(person);
            for(String character : actor.getCharacters()){
                Character c = new Character();
                c.setName(character);
                movie.addCharacter(c);
                person.addCharacter(c);
                c.setActor(person);
                c.setMovie(movie);
            }
        }

        for(String director : dtoMovie.getDirectors()){
            Person person = persons.findByImdbId(director);
            movie.addDirector(person);
        }

        for(String writer : dtoMovie.getWriters()) {
            Person person = persons.findByImdbId(writer);
            movie.addWriter(person);
        }

        movie.setDescription(dtoMovie.getDescription());
        return movie;
    }
}

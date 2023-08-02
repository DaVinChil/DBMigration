package com.example.yeeboy;

import com.example.yeeboy.data_model.Character;
import com.example.yeeboy.data_model.Genre;
import com.example.yeeboy.data_model.Image;
import com.example.yeeboy.data_model.Movie;
import com.example.yeeboy.data_model.Person;
import com.example.yeeboy.dto.DTOMovie;
import com.example.yeeboy.dto.DTOPerson;
import com.example.yeeboy.dto.ImageDatabase;
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
        System.out.println("______________________________________SAVED ALL PERSONS__________________________________________");System.out.println("______________________________________SAVED ALL PERSONS__________________________________________");System.out.println("______________________________________SAVED ALL PERSONS__________________________________________");System.out.println("______________________________________SAVED ALL PERSONS__________________________________________");System.out.println("______________________________________SAVED ALL PERSONS__________________________________________");System.out.println("______________________________________SAVED ALL PERSONS__________________________________________");System.out.println("______________________________________SAVED ALL PERSONS__________________________________________");
    }

    @PostMapping("/movie")
    public void postMovie(@RequestBody List<DTOMovie> dtoMovies){
        for(DTOMovie dtoMovie : dtoMovies) {
            Movie movie = convertDto(dtoMovie);
//            movies.save(movie);
        }
        System.out.println("################################################### ALL SAVED ###############################################");
        System.out.println("################################################### ALL SAVED ###############################################");
        System.out.println("################################################### ALL SAVED ###############################################");
        System.out.println("################################################### ALL SAVED ###############################################");
        System.out.println("################################################### ALL SAVED ###############################################");
        System.out.println("################################################### ALL SAVED ###############################################");
        System.out.println("################################################### ALL SAVED ###############################################");
    }

    public Person convertDto(DTOPerson dtoPerson){
        Person person = new Person();
        person.setFullName(dtoPerson.getFullName());
        person.setImdbId(dtoPerson.getId());
        if(dtoPerson.getBirthDate() != null && !dtoPerson.getBirthDate().isEmpty() && !dtoPerson.getBirthDate().equals("-")){
            try{
                person.setBirthday(Date.valueOf(dtoPerson.getBirthDate()));
            } catch (IllegalArgumentException e){}

        }
        if(dtoPerson.getBirthPlace() != null) {
            person.setBirthPlace(dtoPerson.getBirthPlace());
        }
        if(dtoPerson.getDeathCause() != null) {
            person.setDeathCause(dtoPerson.getDeathCause());
        }
        if(dtoPerson.getDeathDate() != null && !dtoPerson.getDeathDate().isEmpty() && !dtoPerson.getDeathDate().equals("-")){
            try {
                person.setDeathDate(Date.valueOf(dtoPerson.getDeathDate()));
            } catch (IllegalArgumentException e){}
        }
        if(dtoPerson.getDeathPlace() != null && !dtoPerson.getDeathPlace().isEmpty() && !dtoPerson.getDeathPlace().equals("-")) {
            person.setDeathPlace(dtoPerson.getDeathPlace());
        }
        person.setHeight(dtoPerson.getHeight());
        if(dtoPerson.getGender() != null){
            person.setGender(dtoPerson.getGender());
        }
        ImageDatabase im = dtoPerson.getPhoto();
        Image image = new Image();
        image.setHeight((int) im.getHeight());
        image.setWidth((int) im.getWidth());
        image.setUrl(im.getUrl());

        person.setPhoto(image);

        return person;
    }

    public Movie convertDto(DTOMovie dtoMovie){
        Movie movie = new Movie();
        movie.setImdbId(dtoMovie.getId());
        movie.setTitle(dtoMovie.getTitle());
        movie.setChartRating(dtoMovie.getChartRating());
        movies.save(movie);
        for(String genreName : dtoMovie.getGenre()) {
            if(genres.existsByName(genreName)){
                Genre genre = genres.findByName(genreName);
                movie.addGenre(genre);
                genre.addMovie(movie);
            } else {
                Genre genre = new Genre();
                genre.setName(genreName);
                movie.addGenre(genre);
                genre.addMovie(movie);
            }
        }
        movie.setYear((int) dtoMovie.getYear());
        movie.setRunningTimeInMinutes((int) dtoMovie.getRunningTimeInMinutes());

        ImageDatabase im = dtoMovie.getImage();
        Image image = new Image();
        image.setHeight((int) im.getHeight());
        image.setWidth((int) im.getWidth());
        image.setUrl(im.getUrl());

        movie.setImage(image);

        for(DTOMovie.Actor actor : dtoMovie.getActors()) {
            Person person = persons.findByImdbId(actor.getId());
            movie.addActor(person);
            for(String character : actor.getCharacters()){
                Character charac = new Character();
                charac.setName(character);
                charac.setActor(person);
                charac.setMovie(movie);
                movie.addCharacter(charac);
                person.addCharacter(charac);
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

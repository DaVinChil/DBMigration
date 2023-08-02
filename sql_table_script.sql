create table genres
(
    genre_id int primary key auto_increment not null,
    name     varchar(255)                   not null
);

create table images
(
    image_id  int primary key auto_increment not null,
    height    int                            not null,
    width     int                            not null,
    url text
);

create table persons
(
    person_id   int primary key auto_increment not null,
    full_name   varchar(255)                   not null,
    gender      varchar(255),
    height      double,
    birthday    date,
    birth_place varchar(255),
    death_cause varchar(255),
    death_place varchar(255),
    death_date  date,
    photo_id    int,
    imdb_id     varchar(20),
    foreign key (photo_id) references images (image_id) on delete cascade
);

create table movies
(
    movie_id                int primary key auto_increment not null,
    title                   varchar(200)                   not null,
    description             text,
    running_time_in_minutes int,
    chart_rating            double,
    year                    int,
    imdb_id                 varchar(20),
    image_id                int,
    foreign key (image_id) references images (image_id) on delete cascade
);


create table characters
(
    character_id int primary key auto_increment not null,
    name         varchar(255)                   not null,
    movie_id     int                            not null,
    actor_id     int                            not null,
    foreign key (movie_id) references movies (movie_id),
    foreign key (actor_id) references persons (person_id)
);


create table movies_writers
(
    movie_id  int not null,
    person_id int not null,
    primary key (movie_id, person_id)
);

create table movies_directors
(
    movie_id  int not null,
    person_id int not null,
    primary key (movie_id, person_id)
);

create table movies_genres
(
    movie_id int not null,
    genre_id int not null,
    primary key (movie_id, genre_id)
);

create table movies_actors
(
    movie_id  int not null,
    person_id int not null,
    primary key (movie_id, person_id)
);

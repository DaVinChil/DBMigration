create table Genre
(
    genre_id int primary key auto_increment not null,
    name     varchar(255)                   not null
);

create table Image
(
    image_id  int primary key auto_increment not null,
    height    int                            not null,
    width     int                            not null,
    image_url text
);

create table Person
(
    person_id   int primary key auto_increment not null,
    full_name   varchar(255)                   not null,
    gender      varchar(255),
    height      double,
    birthday    date,
    birth_place varchar(255),
    death_cause varchar(255),
    death_date  date,
    photo_id    int,
    imdb_id     varchar(20),
    foreign key (photo_id) references Image (image_id) on delete cascade
);

create table Movie
(
    movie_id                int primary key auto_increment not null,
    title                   varchar(200)                   not null,
    description             text,
    running_time_in_minutes int,
    chart_rating            double,
    year                    int,
    imdb_id                 varchar(20),
    image_id                int,
    foreign key (image_id) references Image (image_id) on delete cascade
);


create table `character`
(
    character_id int primary key auto_increment not null,
    name         varchar(255)                   not null,
    movie_id     int                            not null,
    actor_id     int                            not null,
    foreign key (movie_id) references Movie (movie_id) on delete cascade,
    foreign key (actor_id) references Person (person_id) on delete cascade
);


create table Movie_Writer
(
    movie_id  int not null,
    person_id int not null,
    primary key (movie_id, person_id)
);

create table Movie_Director
(
    movie_id  int not null,
    person_id int not null,
    primary key (movie_id, person_id)
);

create table Movie_Genre
(
    movie_id int not null,
    genre_id int not null,
    primary key (movie_id, genre_id)
);

create table Movie_Actor
(
    movie_id  int not null,
    person_id int not null,
    primary key (movie_id, person_id)
);
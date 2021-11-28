package com.vapsi.app.demo.movies.service;

import com.vapsi.app.demo.movies.dtos.MovieDto;
import com.vapsi.app.demo.movies.entities.MovieEntity;
import com.vapsi.app.demo.movies.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MoviesService {
    MoviesRepository moviesRepository;

    @Autowired
    public MoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public List<MovieDto> getAllMovies() {
        List<MovieDto> movies = new ArrayList<>();

        for (MovieEntity movieEntity : moviesRepository.findAll()) {
            movies.add(MovieDto.dtoFrom(movieEntity));
        }

        return movies;
    }

    public MovieDto saveMovie(MovieDto movieDto) {
        MovieEntity movieEntity = MovieEntity.entityFrom(movieDto);
        MovieEntity savedMovieEntity = moviesRepository.save(movieEntity);
        return MovieDto.dtoFrom(savedMovieEntity);
    }

    public Optional<MovieDto> getMovie(String id) {
        Optional<MovieEntity> movieEntity = moviesRepository.findById(id);
        return movieEntity.map(MovieDto::dtoFrom);
    }

    public MovieDto updateMovie(String id,MovieDto movieDto) {

        MovieEntity movieEntity = MovieEntity.entityFrom(movieDto);
        Optional<MovieEntity> toUpdateMovieEntity = moviesRepository.findById(id);

        MovieEntity valuesToUpdate = new MovieEntity(id, movieEntity.getName(), movieEntity.getActorName(), movieEntity.getDirectorName());
        MovieEntity updatedMovieEntity = moviesRepository.save(valuesToUpdate);

        return MovieDto.dtoFrom(updatedMovieEntity);

    }

    public List<MovieDto> findByActors(List<String> actors) {
        List<MovieEntity> moviesOfActors = moviesRepository.findByActorName(actors);
        List<MovieDto> moviesDtoList = new ArrayList<MovieDto>();
        for(MovieEntity movies: moviesOfActors){
            moviesDtoList.add(MovieDto.dtoFrom(movies));
        }
        return moviesDtoList;

    }
}

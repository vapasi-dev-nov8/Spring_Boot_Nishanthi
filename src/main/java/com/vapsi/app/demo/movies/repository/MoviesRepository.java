package com.vapsi.app.demo.movies.repository;

import com.vapsi.app.demo.movies.entities.MovieEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MoviesRepository extends CrudRepository<MovieEntity, String> {
    @Query("SELECT movie FROM MovieEntity movie WHERE movie.actorName IN (:actors)")
    List<MovieEntity>findByActorName(List<String> actors);
}

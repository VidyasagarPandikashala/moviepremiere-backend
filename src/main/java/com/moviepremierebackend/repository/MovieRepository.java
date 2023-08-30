package com.moviepremierebackend.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.moviepremierebackend.model.Movie;

import jakarta.transaction.Transactional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    ArrayList<Movie> findByMovieName(String movieName);

    @Query("SELECT m FROM Movie m WHERE m.movieName LIKE %:title%")
    ArrayList<Movie> findByMovieLetter(@Param("title") String title);
    
    @Query("SELECT m FROM Movie m WHERE m.movieId = :id")
    Movie findByMovieId(@Param("id") Long id);
 
//    @Query("SELECT m FROM Movie m ORDER BY m.rating.rating DESC")
//    ArrayList<Movie> findByMovieRatingInDescendingOrder();
}

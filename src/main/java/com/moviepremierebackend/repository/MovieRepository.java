package com.moviepremierebackend.repository;

import java.time.LocalDate;
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

    @Query("SELECT m FROM Movie m WHERE m.releaseDate >= :currentDate ORDER BY m.releaseDate ASC")
    List<Movie> findMoviesReleasedAfter(@Param("currentDate") LocalDate currentDate);

    @Query("SELECT m FROM Movie m WHERE m.releaseDate >= :startDate AND m.releaseDate <= :endDate ORDER BY m.releaseDate DESC")
    List<Movie> findMoviesReleasedLastMonth(@Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    // @Query("SELECT m FROM Movie m ORDER BY m.rating.rating DESC")
    // ArrayList<Movie> findByMovieRatingInDescendingOrder();
}

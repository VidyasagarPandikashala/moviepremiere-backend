package com.moviepremierebackend.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.moviepremierebackend.model.Movie;
import com.moviepremierebackend.model.Rating;
import com.moviepremierebackend.model.RatingId;
import com.moviepremierebackend.model.User;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface RatingRepository extends JpaRepository<Rating, RatingId> {
	
	   @Query("SELECT r FROM Rating r where r.movie.movieId = :movieId")
	    ArrayList<Rating> findByMovieRating(@Param("movieId") long movieId);


}

package com.moviepremierebackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.moviepremierebackend.dto.MovieAverageRating;
import com.moviepremierebackend.model.Rating;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface RatingRepository extends JpaRepository<Rating, Long> {

	@Query("SELECT r FROM Rating r WHERE r.movie.movieId = :movieId")
	List<Rating> findByMovieMovieId(@Param("movieId") long movieId);

	@Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
			"FROM Rating r " +
			"WHERE r.user.userId = :userId AND r.movie.movieId = :movieId")
	public boolean ratingExists(@Param("userId") int userId, @Param("movieId") long movieId);

	@Query("SELECT r FROM Rating r WHERE r.user.userId = :userId AND r.movie.movieId = :movieId")
	public Rating findRatingOfUserIdForMovieId(@Param("userId") int userId, @Param("movieId") long movieId);

	@Query("SELECT r FROM Rating r WHERE r.movie.movieId = :movieId")
	public List<Rating> findAllRating(@Param("movieId") long movieId);

	@Query("SELECT AVG(r.rating) FROM Rating r WHERE r.movie.movieId = :movieId")
	public Float getOverallRating(@Param("movieId") Long movieId);

	@Query("SELECT r.movie.movieId, AVG(r.rating) AS avgRating " +
			"FROM Rating r " +
			"WHERE r.movie.movieId IN :movieIds " +
			"GROUP BY r.movie.movieId " +
			"ORDER BY avgRating DESC")
	public List<MovieAverageRating> findMoviesWithHighestAvgRating(@Param("movieIds") List<Long> movieIds);

	// Add a space between "r.movie.movieId" and "FROM" in the SELECT clause
	@Query("SELECT r.movie.movieId " +
			"FROM Rating r " +
			"WHERE r.movie.movieId IN :movieIds " +
			"GROUP BY r.movie.movieId " +
			"ORDER BY AVG(r.rating) DESC")
	public List<Long> findMoviesIdsWithHighestAvgRating(@Param("movieIds") List<Long> movieIds);

	@Query("SELECT r.movie.movieId FROM Rating r")
	public List<Long> retrieveAllMovieIdsFromRating();
}

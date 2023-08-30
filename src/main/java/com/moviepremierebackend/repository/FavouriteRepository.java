package com.moviepremierebackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.moviepremierebackend.model.Favourite;
import com.moviepremierebackend.model.FavouriteId;
import com.moviepremierebackend.model.Movie;
import com.moviepremierebackend.model.User;

import jakarta.transaction.Transactional;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, FavouriteId> {
	@Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END " + "FROM Favourite f "
			+ "WHERE f.user.userId = :userId AND f.movie.movieId = :movieId")
	public boolean existsByUserIdAndMovieId(@Param("userId") int userId, @Param("movieId") long movieId);

	@Transactional
	@Modifying
	@Query("DELETE FROM Favourite f WHERE f.user.id = :userId")
	public void deleteByUserId(@Param("userId") int userId);

	@Query("SELECT f.movie.movieId FROM Favourite f where f.user.id =:userId")
	public List<Long> fetchByUserId(@Param("userId") int userId);

	@Transactional
	@Modifying
	@Query("DELETE FROM Favourite f WHERE f.user.userId = :userId AND f.movie.movieId = :movieId")
	public void deleteByParameters(@Param("userId") int userId, @Param("movieId") long movieId);
	
	boolean existsByUserAndMovie(User user, Movie movie);
}

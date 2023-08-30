package com.moviepremierebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.moviepremierebackend.model.Movie;
import com.moviepremierebackend.model.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
		@Transactional
	 	@Modifying
	    @Query("UPDATE User u SET u.isLoggedIn = :isLoggedIn WHERE u.userId = :userId")
	  public  void updateLoggedInStatus(int userId, boolean isLoggedIn);
		
		 @Query("SELECT u FROM User u WHERE u.userId = :id")
		  public  User findByUserId(@Param("id") int id);
}

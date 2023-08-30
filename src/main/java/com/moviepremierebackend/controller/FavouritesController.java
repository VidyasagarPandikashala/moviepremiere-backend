package com.moviepremierebackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviepremierebackend.model.Favourite;
import com.moviepremierebackend.service.FavouriteServiceImpl;
import com.moviepremierebackend.utilityFunctions.JwtUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/movie-premiere/v1/favourites")
@RestController
public class FavouritesController {
	
	private final FavouriteServiceImpl favouriteService;
	private final JwtUtil jwtUtil;

	@PostMapping("/onToggle/{movieId}")
	public boolean toggleAction(@PathVariable("movieId")int movieId, @RequestHeader("Authorization") String authorizationHeader) {
		int userId = jwtUtil.getUserIdFromHeader(authorizationHeader);
		return this.favouriteService.toggleAction(userId, movieId);
	}
	
	@GetMapping
	public List<Long> retrieveAllMovieFromUserId(@RequestHeader("Authorization") String authorizationHeader) {
		
		int userId = jwtUtil.getUserIdFromHeader(authorizationHeader);
		List<Long> movieIds =  this.favouriteService.retrieveAllMovieFromUserId(userId);
		return movieIds;
	}
	
	@PostMapping("/movieExist/{movieId}")
	public boolean isFavouriteExist(@PathVariable("movieId")int movieId, @RequestHeader("Authorization")String authorizationHeader) {
		int userId = jwtUtil.getUserIdFromHeader(authorizationHeader);
		System.out.println(this.favouriteService.isfavouriteExist(userId, movieId));
		return this.favouriteService.isfavouriteExist(userId, movieId);
	}
	
}

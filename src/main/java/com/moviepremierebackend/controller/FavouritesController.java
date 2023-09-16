package com.moviepremierebackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.moviepremierebackend.service.FavouriteService;
import com.moviepremierebackend.utilityFunctions.JwtUtil;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/movie-premiere/v1/favourites")
public class FavouritesController {

	private final FavouriteService favouriteService;
	private final JwtUtil jwtUtil;

	public FavouritesController(FavouriteService favouriteService, JwtUtil jwtUtil) {
		this.favouriteService = favouriteService;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/onToggle/{movieId}")
	public boolean toggleAction(@PathVariable("movieId") int movieId,
			@RequestHeader("Authorization") String authorizationHeader) {
		int userId = jwtUtil.getUserIdFromHeader(authorizationHeader);
		return this.favouriteService.toggleAction(userId, movieId);
	}

	@GetMapping
	public List<Long> retrieveAllMovieFromUserId(@RequestHeader("Authorization") String authorizationHeader) {
		int userId = jwtUtil.getUserIdFromHeader(authorizationHeader);
		List<Long> movieIds = this.favouriteService.retrieveAllMovieFromUserId(userId);
		return movieIds;
	}

	@PostMapping("/movieExist/{movieId}")
	public boolean isFavouriteExist(@PathVariable("movieId") int movieId,
			@RequestHeader("Authorization") String authorizationHeader) {
		int userId = jwtUtil.getUserIdFromHeader(authorizationHeader);
		return this.favouriteService.isfavouriteExist(userId, movieId);
	}
}
package com.moviepremierebackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviepremierebackend.model.Rating;
import com.moviepremierebackend.service.RatingService;
import com.moviepremierebackend.utilityFunctions.JwtUtil;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/movie-premiere/v1/rating")
@RestController
public class RatingController {

    private final RatingService ratingService;
    private final JwtUtil jwtUtil;

    @PostMapping("/save-rating")
    public void saveUserRating(@RequestBody Rating rating, @RequestHeader("Authorization") String authorizationHeader,
            @RequestHeader("Movieid") long movieId) {
        int userId = jwtUtil.getUserIdFromHeader(authorizationHeader);
        this.ratingService.saveUserRating(rating, userId, movieId);
    }

    @PostMapping("/ratingExist")
    public boolean isRatingExists(@RequestHeader("Authorization") String authorizationHeader,
            @RequestHeader("Movieid") long movieId) {
        int userId = jwtUtil.getUserIdFromHeader(authorizationHeader);
        boolean data = this.ratingService.isRatingExists(userId, movieId);
        return data;
    }

    @PostMapping("/user-rating")
    public Rating getRatingByUser(@RequestHeader("Authorization") String authorizationHeader,
            @RequestHeader("Movieid") long movieId) {
        int userId = jwtUtil.getUserIdFromHeader(authorizationHeader);

        return this.ratingService.fetchRatingForUser(userId, movieId);
    }

    @PostMapping("/all-rating")
    public List<Rating> gatAllRating(@RequestHeader("Movieid") long movieId) {

        return this.ratingService.fetchAllRating(movieId);
    }

}

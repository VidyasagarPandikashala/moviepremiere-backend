package com.moviepremierebackend.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.moviepremierebackend.dto.MovieAverageRating;
import com.moviepremierebackend.model.Rating;

@Service
public interface RatingService {
    public void saveUserRating(Rating rating, int userId, long movieId);

    public boolean isRatingExists(int userId, long movieId);

    public Rating fetchRatingForUser(int userId, long movieId);

    public List<Rating> fetchAllRating(long movieId);

    public List<MovieAverageRating> fetchTopRatedMovies();

}

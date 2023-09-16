package com.moviepremierebackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.moviepremierebackend.dto.MovieAverageRating;
import com.moviepremierebackend.model.Movie;
import com.moviepremierebackend.model.Rating;
import com.moviepremierebackend.model.User;
import com.moviepremierebackend.repository.RatingRepository;
import com.moviepremierebackend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final UserService userService;
    private final MovieService movieService;

    @Override
    public void saveUserRating(Rating rating, int userId, long movieId) {
        // TODO Auto-generated method stub
        this.ratingRepository.save(createRatingObject(rating, userId, movieId));

    }

    public Rating createRatingObject(Rating rating, int userId, long movieId) {
        Rating ratingObj = new Rating();
        ratingObj.setRating((rating.getRating()));
        ratingObj.setDescription((rating.getDescription()));
        ratingObj.setMovie(createMovieObj(movieId));
        ratingObj.setUser(createUserObj(userId));
        return ratingObj;

    }

    public User createUserObj(int userId) {
        User user = new User();
        user = this.userService.getUserById(userId);
        return user;

    }

    public Movie createMovieObj(long movieId) {
        Movie movie = new Movie();
        movie = this.movieService.fetchMovieById(movieId);
        return movie;

    }

    @Override
    public boolean isRatingExists(int userId, long movieId) {

        return this.ratingRepository.ratingExists(userId, movieId);
    }

    @Override
    public Rating fetchRatingForUser(int userId, long movieId) {

        return this.ratingRepository.findRatingOfUserIdForMovieId(userId, movieId);
    }

    @Override
    public List<Rating> fetchAllRating(long movieId) {
        return this.ratingRepository.findAllRating(movieId);
    }

    @Override
    public List<MovieAverageRating> fetchTopRatedMovies() {
        ArrayList<Long> movieIds = (ArrayList<Long>) this.ratingRepository.retrieveAllMovieIdsFromRating();
        List<MovieAverageRating> topMovies = this.ratingRepository.findMoviesWithHighestAvgRating(movieIds);
        return topMovies;
    }
}

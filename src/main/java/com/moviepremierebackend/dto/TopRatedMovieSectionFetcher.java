package com.moviepremierebackend.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moviepremierebackend.model.Movie;
import com.moviepremierebackend.repository.MovieRepository;
import com.moviepremierebackend.repository.RatingRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class TopRatedMovieSectionFetcher implements MovieSectionFetcher {

	@Autowired
	private final MovieRepository movieRepository;

	@Autowired
	private final RatingRepository ratingRepository;

	@Override
	public ArrayList<Movie> fetchMovie() {

		List<Long> currentlyRatedMovieIds = this.ratingRepository.retrieveAllMovieIdsFromRating();
		List<Long> curretlyTopRatedMovieIds = this.ratingRepository
				.findMoviesIdsWithHighestAvgRating(currentlyRatedMovieIds);

		ArrayList<Movie> topRatedMovies = (ArrayList<Movie>) this.movieRepository.findAllById(curretlyTopRatedMovieIds);

		return topRatedMovies;
	}

}
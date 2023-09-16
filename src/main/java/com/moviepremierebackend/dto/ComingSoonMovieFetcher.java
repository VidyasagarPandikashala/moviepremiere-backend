package com.moviepremierebackend.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.moviepremierebackend.model.Movie;
import com.moviepremierebackend.repository.MovieRepository;

@Component
public class ComingSoonMovieFetcher implements MovieSectionFetcher {

	private final MovieRepository movieRepository;

	@Autowired
	public ComingSoonMovieFetcher(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public ArrayList<Movie> fetchMovie() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		LocalDate currentDate = LocalDate.now();

		System.out.println("Today's Date is........................." + currentDate);

		ArrayList<Movie> filteredMovies = (ArrayList<Movie>) this.movieRepository.findMoviesReleasedAfter(currentDate);

		return filteredMovies;
	}

}

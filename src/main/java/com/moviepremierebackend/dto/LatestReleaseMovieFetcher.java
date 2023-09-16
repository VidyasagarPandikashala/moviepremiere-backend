package com.moviepremierebackend.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.moviepremierebackend.model.Movie;
import com.moviepremierebackend.repository.MovieRepository;
import com.moviepremierebackend.utilityFunctions.ArrayListUtitlity;
import com.moviepremierebackend.utilityFunctions.PaginationUtils;

@Component
public class LatestReleaseMovieFetcher implements MovieSectionFetcher {

	private final MovieRepository movieRepository;

	@Autowired
	public LatestReleaseMovieFetcher(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public ArrayList<Movie> fetchMovie() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		LocalDate currentDate = LocalDate.now();
		LocalDate oneMonthAgo = currentDate.minusMonths(1);
		ArrayList<Movie> filteredMovies = (ArrayList<Movie>) this.movieRepository
				.findMoviesReleasedLastMonth(oneMonthAgo, currentDate);
		return filteredMovies;

	}
}

package com.moviepremierebackend.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moviepremierebackend.model.Movie;
import com.moviepremierebackend.repository.MovieRepository;
import com.moviepremierebackend.utilityFunctions.ArrayListUtitlity;
import com.moviepremierebackend.utilityFunctions.PaginationUtils;

@Component
public class LatestReleaseMovieFetcher implements MovieSectionFetcher {

	@Autowired
	private final MovieRepository movieRepository;

	public LatestReleaseMovieFetcher(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public ArrayList<Movie> fetchMovie() {
		
		
		ArrayList<Movie> movies = (ArrayList<Movie>) this.movieRepository.findAll();
		LocalDate currentDate = LocalDate.now();
		LocalDate oneMonthAgo = currentDate.minusMonths(4);

		return new ArrayList<>(movies.stream()
		        .filter(movie -> movie.getReleaseDate().isAfter(oneMonthAgo.minusDays(1))
		                && movie.getReleaseDate().isBefore(currentDate.plusDays(1)))
		        .collect(Collectors.toList()));
	}
}

package com.moviepremierebackend.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moviepremierebackend.model.Movie;
import com.moviepremierebackend.repository.MovieRepository;

@Component
public class ComingSoonMovieFetcher implements MovieSectionFetcher{
	
	@Autowired
	private final MovieRepository movieRepository;
	
	public ComingSoonMovieFetcher (MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	
	
	
	@Override
	public ArrayList<Movie> fetchMovie() {
		 LocalDate currentDate = LocalDate.now();
		 System.out.println(currentDate);
		 ArrayList<Movie> movies = (ArrayList<Movie>) this.movieRepository.findAll();
		 
		ArrayList<Movie>filteredMovies =  (ArrayList<Movie>) movies.stream()
																   .filter(movie->  movie.getReleaseDate().isAfter(currentDate))
																   .collect(Collectors.toList());
		System.out.println(filteredMovies);
			
			return filteredMovies;
	}

}

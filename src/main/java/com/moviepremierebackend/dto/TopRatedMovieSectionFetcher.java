package com.moviepremierebackend.dto;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moviepremierebackend.model.Movie;
import com.moviepremierebackend.repository.MovieRepository;

@Component
public class TopRatedMovieSectionFetcher implements MovieSectionFetcher{
	
	@Autowired
	private final MovieRepository movieRepository;
	
	public TopRatedMovieSectionFetcher (MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	
	
	
	
	@Override
	public ArrayList<Movie> fetchMovie() {
		return null;
		}
}
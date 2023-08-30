package com.moviepremierebackend.dto;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.moviepremierebackend.model.Movie;

@Component
public interface MovieSectionFetcher {
	public ArrayList<Movie> fetchMovie();
}

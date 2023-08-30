package com.moviepremierebackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.moviepremierebackend.dto.MovieRetrieveDto;
import com.moviepremierebackend.dto.MovieSectionDto;
import com.moviepremierebackend.dto.SearchDto;
import com.moviepremierebackend.model.Movie;

@Service
public interface MovieService {

	public void saveMovie(Movie movie);
	
	public void saveMovies(ArrayList<Movie> movies);

	public ArrayList<Movie> fetchAllMovies();

	public void saveMultipleMovies(List<Movie> movie);

	public ArrayList<SearchDto> fetchMovieListBySearchData(String movieName, long movieId, String sort, int size,
			int page);	
	
	public ArrayList<MovieSectionDto> fetchMovieListBySection(String sectionName, String sort, int size,
			int page);

	public Movie fetchMovieById(long movieId);

	public void deleteMovie(long movieId);

}

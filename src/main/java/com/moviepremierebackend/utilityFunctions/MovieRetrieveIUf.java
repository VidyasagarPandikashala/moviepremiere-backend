package com.moviepremierebackend.utilityFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.moviepremierebackend.dto.MovieRetrieveDto;
import com.moviepremierebackend.dto.SearchDto;
import com.moviepremierebackend.model.Movie;


@Component
public class MovieRetrieveIUf {
	

	
	public<T,R>  ArrayList<R> movieBuilder(ArrayList<Movie> movies, Function<Movie, R> dtoBuilder) {
	    List<R> retrieveMovieData = movies.stream()
	            .map(dtoBuilder)
	            .collect(Collectors.toList());
	    

	    return new ArrayList<R>(retrieveMovieData);
	}
	
//	public  ArrayList<SearchDto> movieBuilder(ArrayList<Movie> movies, SearchDto searchDto) {
//	    List<SearchDto> retrieveMovieData = movies.stream().map(movie-> searchDto.builder().movieId(movie.getMovieId())
//	    		.movieName(movie.getMovieName())
//	    		.imageUrl(movie.getImageUrl())
//	    		.year(movie.getYear()).build()).collect(Collectors.toList());
//	    
//	    return new ArrayList<SearchDto>(retrieveMovieData);
//	}
//	    .movieId(movie.getMovieId())
//		.movieName(movie.getMovieName())
//		.imageUrl(movie.getImageUrl())
//		.year(movie.getYear())
//		.build()).collect(Collectors.toList());
}

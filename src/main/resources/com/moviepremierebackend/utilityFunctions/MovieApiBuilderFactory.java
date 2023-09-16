package com.moviepremierebackend.utilityFunctions;

import java.util.ArrayList;
import java.util.function.Function;

import com.moviepremierebackend.dto.MovieRetrieveDto;
import com.moviepremierebackend.dto.MovieSectionDto;
import com.moviepremierebackend.dto.SearchDto;
import com.moviepremierebackend.model.Movie;
import com.moviepremierebackend.model.Rating;

public class MovieApiBuilderFactory {
	
	
	public Function <Movie,MovieRetrieveDto> movieRetrieveDtoBuilder() {
		MovieRetrieveDto movieRetrieveDto = new MovieRetrieveDto();
		
		 return movie -> movieRetrieveDto.builder()
                .movieName(movie.getMovieName())
                .genre(movie.getGenres())
                .movieDescription(movie.getMovieDescription())
                .imageUrl(movie.getImageUrl())
                .actors(movie.getActors())
                .year(movie.getYear())
                .releaseDate(movie.getReleaseDate())
                .ott(movie.getOtt())
                .favourites(movie.getFavourite())
                .rating(movie.getRating())
                .build();
	}
	public Function <Movie,SearchDto> SearchDtoBuilder() {
		SearchDto searchDto = new SearchDto();
		 return movie ->  searchDto.builder()
                .movieName(movie.getMovieName())
                .movieId(movie.getMovieId())
                .imageUrl(movie.getImageUrl())
                .year(movie.getYear())               
                .build();
	}

	public Function <Movie,MovieSectionDto> MovieSectionDtoBuilder() {
		MovieSectionDto movieSectionDto = new MovieSectionDto();
		 return movie ->  movieSectionDto.builder()
                .movieName(movie.getMovieName())
                .movieId(movie.getMovieId())
                .imageUrl(movie.getImageUrl())
                .year(movie.getYear())    
                .rating(movie.getRating())
                .releaseDate(movie.getReleaseDate())
                .build();
	}
	

}

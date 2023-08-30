package com.moviepremierebackend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moviepremierebackend.dto.MovieRetrieveDto;
import com.moviepremierebackend.dto.MovieSectionDto;
import com.moviepremierebackend.dto.SearchDto;
import com.moviepremierebackend.model.Movie;
import com.moviepremierebackend.service.MovieService;
import com.moviepremierebackend.service.MovieServiceImpl;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/movie-premiere/v1/")
@RestController
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@PostMapping("/save")
	public void saveMovieData(@RequestBody Movie movie) {
		this.movieService.saveMovie(movie);
	}
	
	@PostMapping
	public void saveAllMovieData(@RequestBody ArrayList<Movie> movie) {
		this.movieService.saveMovies(movie);
	}
	
	
	@GetMapping("/movie/search")
	public ArrayList<SearchDto> getRequiredData(@RequestParam(name="id", defaultValue = "0")long movieId ,
									   @RequestParam(name ="movieName", required = false) String movieName, 
									   @RequestParam(value="sort", defaultValue = "ASC") String sort,
									@RequestParam(name="size", defaultValue ="2") int size,
									@RequestParam(name="page", defaultValue ="1") int page)
	{
	
		return this.movieService.fetchMovieListBySearchData(movieName, movieId, sort, size, page);
	}
	@GetMapping("/movie/get-all-movies")
	public ArrayList<Movie> getAllMovies(){
		
		return this.movieService.fetchAllMovies();
		
	}
	@GetMapping("/movie/byId/{id}")
	public Movie getAllMovieId(@PathVariable("id") long movieId){
		System.out.println(movieId);
		
		return this.movieService.fetchMovieById(movieId);
		
	}
	
	
	@GetMapping("/movie/get-movies-by-section")
	public ArrayList<MovieSectionDto> getMoviesForSection(@RequestParam(name="name", defaultValue= "null", required = false)String sectionName,
			@RequestParam(name="sort", defaultValue= "null", required = false)String sort,
			@RequestParam(name="size", defaultValue ="0")int size,
			@RequestParam(name="page",defaultValue ="1")int page){
		ArrayList<MovieSectionDto> searchResult = this.movieService.fetchMovieListBySection(sectionName, sort, size, page);
		System.out.println(searchResult);
		return this.movieService.fetchMovieListBySection(sectionName, sort, size, page);
		
	}
	
	@GetMapping("/delete-movie/{movieId}")
	public void deleteMovie(@PathVariable("movieId")long movieId) {
		this.movieService.deleteMovie(movieId);
	}
}
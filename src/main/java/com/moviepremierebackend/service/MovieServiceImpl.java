package com.moviepremierebackend.service;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviepremierebackend.dto.MovieRetrieveDto;
import com.moviepremierebackend.dto.MovieSectionDto;
import com.moviepremierebackend.dto.MovieSectionFetcher;
import com.moviepremierebackend.dto.MoviezUiDto;
import com.moviepremierebackend.dto.SearchDto;
import com.moviepremierebackend.factory.MovieSectionFactory;
import com.moviepremierebackend.model.Movie;
import com.moviepremierebackend.model.Ott;
import com.moviepremierebackend.model.Rating;
import com.moviepremierebackend.repository.MovieRepository;
import com.moviepremierebackend.repository.OttRepository;
import com.moviepremierebackend.repository.RatingRepository;
import com.moviepremierebackend.utilityFunctions.ArrayListUtitlity;
import com.moviepremierebackend.utilityFunctions.MovieApiBuilderFactory;
import com.moviepremierebackend.utilityFunctions.MovieRetrieveIUf;
import com.moviepremierebackend.utilityFunctions.PaginationUtils;
import com.moviepremierebackend.utilityFunctions.UtilityFunction;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service("MovieService")
public class MovieServiceImpl implements MovieService {

	private final MovieRepository movieRepository;

	private final OttService ottService;

	private final RatingRepository ratingRepository;

	private final MovieRetrieveIUf retrievedData;

	private final MovieSectionFactory sectionFetcherFactory;

	public MovieServiceImpl(MovieRepository movieRepository, OttService ottService, MovieRetrieveIUf retrievedData,
			MovieSectionFactory sectionFetcherFactory, RatingRepository ratingRepository) {
		this.movieRepository = movieRepository;
		this.ottService = ottService;
		this.retrievedData = retrievedData;
		this.sectionFetcherFactory = sectionFetcherFactory;
		this.ratingRepository = ratingRepository;

	}

	@Override
	public void saveMovie(Movie movie) {

		 ottService.setOttToMovie(movie);
		movieRepository.save(movie);
		System.out.println("Movie saved to the database");
	}


	public ArrayList<Movie> fetchAllMovies() {
		ArrayList<Movie> movies = (ArrayList<Movie>) movieRepository.findAll();
		for (Movie movie : movies) {
			movie.getRating().size(); // Eagerly fetch ratings
		}
		return movies;
	}
//	

	@Override
	public void saveMultipleMovies(List<Movie> movies) {
		// TODO Auto-generated method stub
		this.movieRepository.saveAll(movies);

	}

	@Override
	public ArrayList<SearchDto> fetchMovieListBySearchData(String movieName, long movieId, String sort, int size,
			int page) {
		// TODO Auto-generated method stub
		if( movieName!=null || movieName.length()>=	3) {
		
		String loweredCaseMovieName = movieName.toLowerCase();

		MovieApiBuilderFactory movieApiBuilder = new MovieApiBuilderFactory();

		ArrayList<Movie> filteredMovies = new ArrayList<>(movieRepository.findByMovieLetter(loweredCaseMovieName));
		HashMap<String, Integer> indexes = PaginationUtils.calculateStartIndexAndLastIndex1(page, size);

		ArrayList<Movie> slicedMovies = ArrayListUtitlity.sliceData(filteredMovies,
				indexes.get(PaginationUtils.startIndex), indexes.get(PaginationUtils.endIndex));
		return this.retrievedData.movieBuilder(slicedMovies, movieApiBuilder.SearchDtoBuilder());
		}
		return new ArrayList<>();
	}

	@Override
	public ArrayList<MovieSectionDto> fetchMovieListBySection(String sectionName, String sort, int size, int page) {
		

		MovieApiBuilderFactory movieApiBuilder = new MovieApiBuilderFactory();
		MovieSectionFetcher movieSectionFetcher = sectionFetcherFactory.createFetcher(sectionName);
		ArrayList<Movie> moviesRetrievedForSection = movieSectionFetcher.fetchMovie();
		

		HashMap<String, Integer> indexes = PaginationUtils.calculateStartIndexAndLastIndex1(page, size);
		ArrayList<Movie> slicedMovies = ArrayListUtitlity.sliceData(moviesRetrievedForSection,
				indexes.get(PaginationUtils.startIndex), indexes.get(PaginationUtils.endIndex));

		return new ArrayList<MovieSectionDto>(
				this.retrievedData.movieBuilder(slicedMovies, movieApiBuilder.MovieSectionDtoBuilder()));
	}

	@Override
	public void saveMovies(ArrayList<Movie> movies) {
		this.movieRepository.saveAll(movies);
		
	}

	@Override
	public Movie fetchMovieById(long movieId) {
		// TODO Auto-generated method stub
		return this.movieRepository.findByMovieId(movieId);
	}

	@Override
	public void deleteMovie(long movieId) {
		// TODO Auto-generated method stub
		
		Movie movie = this.movieRepository.findByMovieId(movieId);
		this.movieRepository.delete(movie);
	}

}

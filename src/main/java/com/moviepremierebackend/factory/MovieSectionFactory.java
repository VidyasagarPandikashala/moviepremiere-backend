package com.moviepremierebackend.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moviepremierebackend.dto.ComingSoonMovieFetcher;
import com.moviepremierebackend.dto.LatestReleaseMovieFetcher;
import com.moviepremierebackend.dto.MovieSectionFetcher;
import com.moviepremierebackend.dto.TopRatedMovieSectionFetcher;
import com.moviepremierebackend.repository.MovieRepository;

@Component
public class MovieSectionFactory {

	private final MovieRepository movieRepository;

	@Autowired
	public MovieSectionFactory(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public MovieSectionFetcher createFetcher(String sectionName) {
		String loweredCaseSectionName = sectionName.toLowerCase();
		if (loweredCaseSectionName.equals("comingsoon")) {
			return new ComingSoonMovieFetcher(movieRepository);
		} else if (loweredCaseSectionName.equals("toprated")) {
			return new TopRatedMovieSectionFetcher(movieRepository);
		} else if (loweredCaseSectionName.equals("latestrelease")) {
			return new LatestReleaseMovieFetcher(movieRepository);
		} else {
			throw new IllegalArgumentException("Invalid section name: " + sectionName);
		}
	}
}
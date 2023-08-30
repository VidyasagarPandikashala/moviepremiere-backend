package com.moviepremierebackend.dto;

import java.time.LocalDate;
import java.util.List;

import com.moviepremierebackend.model.Favourite;
import com.moviepremierebackend.model.Ott;
import com.moviepremierebackend.model.Rating;
import com.moviepremierebackend.model.User;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieRetrieveDto {
	private long movieId;
	private String movieName;

	private List<String> genre;
	private String movieDescription;
	private String imageUrl;

	private List<String> actors;
	private int year;
	private LocalDate releaseDate;

	private Ott ott;

	private List<Favourite> favourites;

	private List<Rating> rating;

}

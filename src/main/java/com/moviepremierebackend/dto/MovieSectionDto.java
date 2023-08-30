package com.moviepremierebackend.dto;

import java.time.LocalDate;
import java.util.List;

import com.moviepremierebackend.model.Rating;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieSectionDto {
	private long movieId;
	private String movieName;
	private String imageUrl;
	private int year;
	private List<Rating> rating;
	private LocalDate releaseDate;
}

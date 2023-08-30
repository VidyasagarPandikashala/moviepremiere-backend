package com.moviepremierebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto {
	private long movieId;
	private String movieName;
	private String imageUrl;
	private int year;

}

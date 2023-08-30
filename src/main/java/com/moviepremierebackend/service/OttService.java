package com.moviepremierebackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.moviepremierebackend.model.Movie;
import com.moviepremierebackend.model.Ott;
@Service
public interface OttService {
	public void setOttToMovie(Movie movie);
	public void saveOtt(Ott ott);
	public Ott findByOttName (String ottName);
	public Boolean ottExist (Ott ott);
}

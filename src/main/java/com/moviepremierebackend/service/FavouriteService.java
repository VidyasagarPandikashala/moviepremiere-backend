package com.moviepremierebackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moviepremierebackend.model.Favourite;

@Service
public interface FavouriteService {
	public void saveFavourites(int userId, long movieId);

	public void removeFavouritesWithParamater(int userId, long movieId);

	public void removeFavourites(Favourite favourite);

	public boolean toggleAction(int userId, long movieId);

	public List<Long> retrieveAllMovieFromUserId(int userId);

	public boolean isfavouriteExist(int userId, long movieId);

}

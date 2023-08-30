package com.moviepremierebackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moviepremierebackend.model.Favourite;
import com.moviepremierebackend.model.Movie;
import com.moviepremierebackend.model.User;
import com.moviepremierebackend.repository.FavouriteRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FavouriteServiceImpl implements FavourtieService {

	private final FavouriteRepository favouriteRepo;
	private final UserService userService;
	private final MovieService movieService;

    @Override
    @Transactional
    public void saveFavourites(int userId, long movieId) {
        User user = userService.getUserById(userId);
        Movie movie = movieService.fetchMovieById(movieId);

        if (!favouriteRepo.existsByUserAndMovie(user, movie)) {
            Favourite favourite = new Favourite();
            favourite.setUser(user);
            favourite.setMovie(movie);
            favouriteRepo.save(favourite);
        }
    }
	@Override
	public void removeFavouritesWithParamater(int userId, long movieId) {
		Favourite favourite = createFavouriteObjectFromParameter(userId, movieId);
		// TODO Auto-generated method stub
		removeFavourites(favourite);
	}

	@Override
	public void removeFavourites(Favourite favourite) {
		// TODO Auto-generated method stub
		favouriteRepo.delete(favourite);

	}

	public void removeFavourites(int userId, long movieId) {
		// TODO Auto-generated method stub
		favouriteRepo.deleteByParameters(userId, movieId);

	}

	public boolean isfavouriteExist(int userId, long movieId) {
		return favouriteRepo.existsByUserIdAndMovieId(userId, movieId);

	}

	public boolean toggleAction(int userId, long movieId) {
		Favourite favourite = createFavouriteObjectFromParameter(userId, movieId);
		if (isfavouriteExist(userId, movieId)) {
			removeFavourites(userId, movieId);

		} else {
			saveFavourites(userId, movieId);

		}
		return isfavouriteExist(userId, movieId);
	}

	public List<Long> retrieveAllMovieFromUserId(int userId) {

		return this.favouriteRepo.fetchByUserId(userId);
		// TODO Auto-generated method stub

	}

	public Favourite createFavouriteObjectFromParameter(int userId, long movieId) {

		User user = this.userService.getUserById(userId);
		Movie movie = movieService.fetchMovieById(movieId);

		Favourite favourite = new Favourite(user, movie);

		return favourite;
	}

}

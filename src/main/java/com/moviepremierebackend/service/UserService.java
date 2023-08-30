package com.moviepremierebackend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.moviepremierebackend.model.AuthenticationResponse;
import com.moviepremierebackend.model.Movie;
import com.moviepremierebackend.model.User;

@Service
public interface UserService {

	public String saveUser(User user);

	public AuthenticationResponse getAuthentication(User user);

	public void setLoggedInandLoggedOutStatus(int userId, boolean authentication);

	public User getUserById(int userId);

}

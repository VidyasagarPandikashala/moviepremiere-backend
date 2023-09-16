package com.moviepremierebackend.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import com.moviepremierebackend.model.AuthenticationResponse;
import com.moviepremierebackend.model.Movie;
import com.moviepremierebackend.model.User;
import com.moviepremierebackend.repository.UserRepository;
import com.moviepremierebackend.utilityFunctions.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;

	@Override
	public String saveUser(User user) {
		// TODO Auto-generated method stub
		User userObject = new User();
		userObject.setEmail(user.getEmail());
		userObject.setUserName(user.getUserName());
		userObject.setPassword(user.getPassword());
		userObject.setLoggedIn(false);

		this.userRepository.save(userObject);
		return "Successfully saved user Data";

	}

	@Override
	public AuthenticationResponse getAuthentication(User user) {
		String userName = user.getUserName();
		String password = user.getPassword();
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		ArrayList<User> users = (ArrayList<User>) this.userRepository.findAll();

		Optional<User> authenticatedUser = users.stream().filter(EachUser -> EachUser.getUserName() != null
				&& EachUser.getUserName().equals(userName) && EachUser.getPassword().equals(password)).findFirst();

		if (authenticatedUser.isPresent()) {
			int authenticatedUserId = authenticatedUser.get().getUserId();
			String username = authenticatedUser.get().getUserName();
			String token = jwtUtil.generateToken(authenticatedUserId, username);

			authenticationResponse.setUserId(authenticatedUserId);
			authenticationResponse.setAuthenticated(true);
			authenticationResponse.setToken(token);
			setLoggedInandLoggedOutStatus(authenticationResponse.getUserId(), authenticationResponse.isAuthenticated());
			System.out.println("Authenticated user ID: " + authenticatedUserId);
			return authenticationResponse;
		} else {
			authenticationResponse.setUserId(-1);
			authenticationResponse.setAuthenticated(false);
			return authenticationResponse;
		}

	}

	public void setLoggedInandLoggedOutStatus(int userId, boolean isAuthorized) {

		this.userRepository.updateLoggedInStatus(userId, isAuthorized);

	}

	@Override
	public User getUserById(int userId) {

		return this.userRepository.findByUserId(userId);

	}

}

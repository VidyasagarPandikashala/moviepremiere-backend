package com.moviepremierebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moviepremierebackend.model.AuthenticationResponse;

import com.moviepremierebackend.model.User;

import com.moviepremierebackend.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/movie-premiere/v1/user")
@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/sign-up")

	public String saveMovieData(@RequestBody User user) {

		this.userService.saveUser(user);
		return "User Data saved successfully";
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public AuthenticationResponse loginConfirmation(@RequestBody User user) {
		return this.userService.getAuthentication(user);
	}

	@PostMapping("/user/logout")
	public void setLoggedInandLoggedOutStatus(@RequestParam(name = "id", required = true) int userId) {

		this.userService.setLoggedInandLoggedOutStatus(userId, false);

	}
}

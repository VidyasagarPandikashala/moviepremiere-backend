package com.moviepremierebackend.model;

import java.util.HashSet;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Ott {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int ottId;
	private String ottName;
	
	@OneToMany(mappedBy="ott")
	private List<Movie> movies;

}

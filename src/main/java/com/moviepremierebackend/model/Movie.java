package com.moviepremierebackend.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.sql.ast.tree.from.MappedByTableGroup;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "movieId")
@ToString(exclude = {"favourite", "rating"})
public class Movie {
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long movieId;
	private String movieName;
	@ElementCollection
    @CollectionTable(name = "genre")
    private List<String> genres;
	private String movieDescription;
	private String imageUrl;
	@ElementCollection
    @CollectionTable(name = "actors")
    private List<String> actors;
	private int year;
	private LocalDate releaseDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Ott ott;
	
	
	@OneToMany(mappedBy = "movie", fetch = FetchType.EAGER)
	@JsonIgnore
	private List <Favourite> favourite;
	
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonBackReference("ratingBackReference")
	private List <Rating> rating;

}

package com.moviepremierebackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(FavouriteId.class)
public class Favourite {
    @Id
    @ManyToOne
    private User user;

    @Id
    @ManyToOne
    private Movie movie;

    // Other fields, constructors, getters, and setters...
}

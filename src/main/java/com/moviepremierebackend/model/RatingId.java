package com.moviepremierebackend.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;

//@Embeddable
@Data
public class RatingId implements Serializable {
	

    private User user;

    private Movie movie;

   
}

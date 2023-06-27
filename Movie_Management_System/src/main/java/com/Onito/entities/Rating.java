package com.Onito.entities;

import com.Onito.entities.dto.MovieAndRating;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ratings", schema = "ratings")
@NoArgsConstructor
public class Rating {
	
	@Id
	@JsonProperty(access = Access.READ_ONLY)
	private String tconst;
	@Min(0)
	private double averageRating;
	@Min(0)
	private Integer numVotes;
	
	public Rating(MovieAndRating movieAndRating) {
		this.averageRating = movieAndRating.getAverageRating();
		this.numVotes = movieAndRating.getNumVotes();
	}
	
}

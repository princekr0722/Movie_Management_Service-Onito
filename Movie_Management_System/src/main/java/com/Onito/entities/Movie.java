package com.Onito.entities;

import com.Onito.entities.dto.MovieAndRating;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
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
@Table(name = "movies", schema = "movies")
@NoArgsConstructor
public class Movie {
	
	@Id
	@JsonProperty(access = Access.READ_ONLY)
	private String tconst;
	
	private String titleType;
	private String primaryTitle;
	@Min(0)
	private Integer runtimeMinutes;
	@Column(name = "genres")
	private String genre;
	
	public Movie(MovieAndRating movieAndRating) {
		this.titleType = movieAndRating.getTitleType();
		this.primaryTitle = movieAndRating.getPrimaryTitle();
		this.runtimeMinutes = movieAndRating.getRuntimeMinutes();
		this.genre = movieAndRating.getGenre();
	}
	
	
}

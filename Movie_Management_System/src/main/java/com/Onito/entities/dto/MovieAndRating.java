package com.Onito.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieAndRating {
	private String titleType;
	private String primaryTitle;
	private Integer runtimeMinutes;
	private String genre;
	
	private double averageRating;	
	private Integer numVotes;
}

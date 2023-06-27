package com.Onito.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * tconst, primaryTitle, genre & averageRating.
 * @author prince
 *
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto2 {
	private String tconst;
	private String primaryTitle;
	private String genre;
	private double averageRating;
}

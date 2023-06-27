package com.Onito.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * tconst, primaryTitle, runtimeMinutes & genres
 * @author prince
 *
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto1 {
	private String tconst;
	private String primaryTitle;
	private Integer runtimeMinutes;
	private String genres;
}

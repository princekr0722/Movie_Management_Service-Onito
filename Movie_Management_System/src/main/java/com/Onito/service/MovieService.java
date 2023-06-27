package com.Onito.service;

import java.util.List;

import com.Onito.entities.dto.GenreWiseMovieAndTotalVotesDto;
import com.Onito.entities.dto.MovieAndRating;
import com.Onito.entities.dto.MovieDto1;
import com.Onito.entities.dto.MovieDto2;

public interface MovieService {
	
	List<MovieDto1> getTop10LongestMovie();
	String saveNewMovie(MovieAndRating movieDetails);
	List<MovieDto2> getMoviesAbove6Rating();
	List<GenreWiseMovieAndTotalVotesDto> showGenreWiseMovieAndTotalVotesDtos();
	String incrementRuntimeMinutesOfAllMovies();
}

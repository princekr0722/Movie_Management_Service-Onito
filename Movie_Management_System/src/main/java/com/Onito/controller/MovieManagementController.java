package com.Onito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Onito.entities.dto.GenreWiseMovieAndTotalVotesDto;
import com.Onito.entities.dto.MovieAndRating;
import com.Onito.entities.dto.MovieDto1;
import com.Onito.entities.dto.MovieDto2;
import com.Onito.service.MovieService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class MovieManagementController {

	@Autowired
	private MovieService movieService;
	
	@GetMapping("/longest-duration-movies")
	public ResponseEntity<List<MovieDto1>> getTop10LongestMovie() {
		List<MovieDto1> top10LongestMovie = movieService.getTop10LongestMovie();
		return new ResponseEntity<List<MovieDto1>>(top10LongestMovie, HttpStatus.OK);
	}

	@PostMapping("/new-movie")
	public ResponseEntity<String> saveNewMovie(@Valid @RequestBody MovieAndRating movieDetails) {
		String message = movieService.saveNewMovie(movieDetails);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping("/top-rated-movies")
	public ResponseEntity<List<MovieDto2>> getMoviesAbove6Rating() {
		List<MovieDto2> moviesAbove6Rating = movieService.getMoviesAbove6Rating();
		return new ResponseEntity<>(moviesAbove6Rating, HttpStatus.OK);
	}

	@GetMapping("/genre-movies-with-subtotals")
	public ResponseEntity<List<GenreWiseMovieAndTotalVotesDto>> showGenreWiseMovieAndTotalVotesDtos() {
		List<GenreWiseMovieAndTotalVotesDto> genreWiseMovieAndTotalVotes = movieService.showGenreWiseMovieAndTotalVotesDtos();
		return new ResponseEntity<List<GenreWiseMovieAndTotalVotesDto>>(genreWiseMovieAndTotalVotes, HttpStatus.OK);
	}

	@PostMapping("/update-runtime-minutes")
	public ResponseEntity<String> incrementRuntimeMinutesOfAllMovies() {
		return new ResponseEntity<String>(movieService.incrementRuntimeMinutesOfAllMovies(), HttpStatus.OK);
	}
	
	
}

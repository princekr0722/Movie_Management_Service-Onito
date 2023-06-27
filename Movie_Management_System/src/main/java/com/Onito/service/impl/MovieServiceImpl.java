package com.Onito.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.Onito.entities.Movie;
import com.Onito.entities.Rating;
import com.Onito.entities.dto.GenreWiseMovieAndTotalVotesDto;
import com.Onito.entities.dto.MovieAndRating;
import com.Onito.entities.dto.MovieDto1;
import com.Onito.entities.dto.MovieDto2;
import com.Onito.repository.MovieRepository;
import com.Onito.repository.RatingRepository;
import com.Onito.service.MovieService;

import jakarta.transaction.Transactional;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private RatingRepository ratingRepository;
	
	@Override
	public List<MovieDto1> getTop10LongestMovie() {
		List<MovieDto1> top10LongestMovie = movieRepository.getTop10LongestMovie(PageRequest.of(0, 10));
		return top10LongestMovie;
	}

	@Override
	public String saveNewMovie(MovieAndRating movieAndRating) {
		Movie movie = new Movie(movieAndRating);
		Rating rating = new Rating(movieAndRating);
		
		String newId = getNewId();
		movie.setTconst(newId);
		rating.setTconst(newId);
		
		movieRepository.save(movie);
		ratingRepository.save(rating);
		
		return "success";
	}

	@Override
	public List<MovieDto2> getMoviesAbove6Rating() {
		List<MovieDto2> moviesAbove6Rating = movieRepository.getMoviesAbove6Rating();
		return moviesAbove6Rating;
	}

	@Override
	public List<GenreWiseMovieAndTotalVotesDto> showGenreWiseMovieAndTotalVotesDtos() {
		List<GenreWiseMovieAndTotalVotesDto> genreWiseMovieAndTotalVotes = movieRepository.getGenreWiseMovieAndTotalVotesDtos();
		return genreWiseMovieAndTotalVotes;
	}

	@Override
	@Transactional
	public String incrementRuntimeMinutesOfAllMovies() {
		movieRepository.incrementRuntimeMinutesOfAllMovies();
		return "incremented runtime";
	}
	
	public String getNewId(){
		// "tt0000001";
		String prefix = "tt";

		List<String> lastIdList = movieRepository.getLastMovieId(PageRequest.of(0, 1));
		String lastId;
		if (lastIdList.size() == 0)
			lastId = "";
		else
			lastId = lastIdList.get(0);

		
		int newId = 1;
		for (int i = 0; i < lastId.length(); i++) {
			char ch = lastId.charAt(i);
			if (ch != 't' && ch != '0') {
				Integer lastIdDigit = Integer.parseInt(lastId.substring(i));
				newId = lastIdDigit + 1;
				break;
			}
		}
		

		StringBuilder sb = new StringBuilder(prefix);
		Integer noOfZeroInPrefix = 7 - (String.valueOf(newId).length());

		while (noOfZeroInPrefix != 0) {
			sb.append(0);
			noOfZeroInPrefix--;
		}
		sb.append(newId);

		return sb.toString();
	}


}

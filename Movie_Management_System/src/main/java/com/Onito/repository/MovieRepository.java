package com.Onito.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.Onito.entities.Movie;
import com.Onito.entities.dto.GenreWiseMovieAndTotalVotesDto;
import com.Onito.entities.dto.MovieDto1;
import com.Onito.entities.dto.MovieDto2;
import com.Onito.entities.dto.PrimaryTitleAndNumVotesDto;

public interface MovieRepository extends JpaRepository<Movie, String>, PagingAndSortingRepository<Movie, String>{
	@Query("SELECT tconst FROM Movie ORDER BY tconst DESC")
	List<String> getLastMovieId(Pageable pageable);
	
	@Query("SELECT new com.Onito.entities.dto.MovieDto1(tconst, primaryTitle, runtimeMinutes, genre) FROM Movie ORDER BY runtimeMinutes DESC")
	List<MovieDto1> getTop10LongestMovie(Pageable pageable);
	
	@Query("SELECT new com.Onito.entities.dto.MovieDto2(m.tconst, m.primaryTitle, genre, r.averageRating) FROM Movie m INNER JOIN Rating r ON m.tconst = r.tconst WHERE r.averageRating > 6 ORDER BY averageRating DESC")
	List<MovieDto2> getMoviesAbove6Rating();
	
	@Query("SELECT m.genre, m.primaryTitle, r.numVotes, SUM(r.numVotes) OVER (PARTITION BY m.genre) "
	        + "FROM Movie m INNER JOIN Rating r ON m.tconst = r.tconst "
	        + "GROUP BY m.genre, m.primaryTitle, r.numVotes")
	List<Object[]> showGenreWiseMovieAndTotalVotesDtos();
	public default List<GenreWiseMovieAndTotalVotesDto> getGenreWiseMovieAndTotalVotesDtos() {
	    List<Object[]> results = showGenreWiseMovieAndTotalVotesDtos();
	    Map<String, GenreWiseMovieAndTotalVotesDto> genreMap = new HashMap<>();

	    for (Object[] result : results) {
	        String genre = (String) result[0];
	        String primaryTitle = (String) result[1];
	        Integer numVotes = (Integer) result[2];
	        Long totalVotes = (Long) result[3];

	        if (!genreMap.containsKey(genre)) {
	            genreMap.put(genre, new GenreWiseMovieAndTotalVotesDto(genre, new ArrayList<>(), totalVotes));
	        }

	        GenreWiseMovieAndTotalVotesDto genreDto = genreMap.get(genre);
	        genreDto.getPrimaryTitleAndNumVotes().add(new PrimaryTitleAndNumVotesDto(primaryTitle, numVotes));
	    }

	    return new ArrayList<>(genreMap.values());
	}

	
	@Modifying
	@Query("UPDATE Movie m SET m.runtimeMinutes = CASE m.genre " +
	        "WHEN 'Documentary' THEN m.runtimeMinutes + 15 " +
	        "WHEN 'Animation' THEN m.runtimeMinutes + 30 " +
	        "ELSE m.runtimeMinutes + 45 END")
	int incrementRuntimeMinutesOfAllMovies();
	
}

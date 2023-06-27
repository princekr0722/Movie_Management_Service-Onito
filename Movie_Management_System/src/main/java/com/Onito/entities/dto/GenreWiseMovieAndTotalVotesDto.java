package com.Onito.entities.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GenreWiseMovieAndTotalVotesDto {
	private String genre;
	private List<PrimaryTitleAndNumVotesDto> primaryTitleAndNumVotes;
//	private PrimaryTitleAndNumVotesDto primaryTitleAndNumVotesDtos;
	private long totalVotes;
	
}

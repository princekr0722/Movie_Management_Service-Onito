package com.Onito.entities.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PrimaryTitleAndNumVotesDtoList {
    private List<PrimaryTitleAndNumVotesDto> primaryTitleAndNumVotesDtos;

    public static PrimaryTitleAndNumVotesDtoList valueOf(String value) {
        List<PrimaryTitleAndNumVotesDto> primaryTitleAndNumVotesDtos = new ArrayList<>();
        String[] parts = value.split(",");
        for (String part : parts) {
            String[] subParts = part.split(":");
            String primaryTitle = subParts[0].trim();
            Integer numVotes = Integer.valueOf(subParts[1].trim());
            PrimaryTitleAndNumVotesDto dto = new PrimaryTitleAndNumVotesDto(primaryTitle, numVotes);
            primaryTitleAndNumVotesDtos.add(dto);
        }
        return new PrimaryTitleAndNumVotesDtoList(primaryTitleAndNumVotesDtos);
    }
}

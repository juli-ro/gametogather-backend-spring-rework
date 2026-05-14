package de.gametogather.gtgspringbackend.model.dto;

import java.util.List;
import java.util.UUID;

public record MeetDto(
        UUID id,
        String name,
        String meetType,
        boolean hasMovies,
        boolean hasGames,
        UUID groupId,

        List<ActivityDto> activities,
        List<MeetDateSuggestionDto> meetDateSuggestions,
        List<MeetUserDto> meetUsers
)

{}

package de.gametogather.gtgspringbackend.model.dto;

import java.util.List;
import java.util.UUID;

public record MeetUserDto(
        UUID id,
        String name,
        boolean isHost,
        boolean isParticipating,

        UserDto User,
        List<MeetUserVoteDto> meetUserVotes
) {
}

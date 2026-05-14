package de.gametogather.gtgspringbackend.model.dto;

import java.time.Instant;
import java.util.UUID;

public record MeetDateSuggestionDto(
        UUID id,
        Instant date,
        boolean isChosenDate,
        //Todo: check if still necessary (MeetController)
        UUID meetId
) {
}

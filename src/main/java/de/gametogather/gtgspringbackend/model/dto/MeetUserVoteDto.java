package de.gametogather.gtgspringbackend.model.dto;

import java.util.UUID;

public record MeetUserVoteDto(
        UUID id,
        Double rating,
        UUID votableItemId,
        String votableItemType
) {
}

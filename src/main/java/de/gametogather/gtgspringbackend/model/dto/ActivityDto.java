package de.gametogather.gtgspringbackend.model.dto;

import java.util.UUID;

public record ActivityDto(
        UUID id,
        String name,
        String activityDescription
) {
}

package de.gametogather.gtgspringbackend.model.dto;

import java.util.UUID;

public record GameLookupDto(
        UUID id,
        String name
) {
}

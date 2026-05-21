package de.gametogather.gtgspringbackend.model.dto;

import java.util.List;
import java.util.UUID;

public record GroupUserDto(
    UUID id,
    String name,
    boolean isGroupAdmin,

    List<GameLookupDto> ownedGames
) {
}

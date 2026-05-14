package de.gametogather.gtgspringbackend.model.dto;

import java.util.List;
import java.util.UUID;


public record UserDto(
        UUID id,
        String name,
        String roleName,

        List<GameDto> games
) {
}

package de.gametogather.gtgspringbackend.model.dto;

import java.util.List;
import java.util.UUID;

public record GroupDto(
        UUID id,
        String name,

        List<GroupUserDto> groupUsers
) {
}

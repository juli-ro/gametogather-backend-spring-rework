package de.gametogather.gtgspringbackend.model.dto;

import java.util.UUID;

public record GameDto(
        UUID id,
        String name,
        int minPlayerNumber,
        int maxPlayerNumber,
        int playTime,
        int yearPublished,
        int minAge
) {}

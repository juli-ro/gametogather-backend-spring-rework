package de.gametogather.gtgspringbackend.mapper;

import de.gametogather.gtgspringbackend.model.dto.GameLookupDto;
import de.gametogather.gtgspringbackend.model.entity.Game;
import de.gametogather.gtgspringbackend.model.entity.UserGame;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GameLookupMapper {
    @Mapping(source = "game.id", target = "id")
    @Mapping(source = "game.name", target = "name")
    GameLookupDto toDto(UserGame userGame);

    GameLookupDto toDto(Game game);
}

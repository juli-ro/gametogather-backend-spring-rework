package de.gametogather.gtgspringbackend.mapper;

import de.gametogather.gtgspringbackend.model.dto.GameDto;
import de.gametogather.gtgspringbackend.model.entity.Game;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {

    GameDto toDto(Game game);

    Game toEntity(GameDto gameDto);
}

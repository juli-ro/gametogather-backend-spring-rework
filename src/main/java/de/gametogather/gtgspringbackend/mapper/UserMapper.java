package de.gametogather.gtgspringbackend.mapper;

import de.gametogather.gtgspringbackend.model.dto.GameDto;
import de.gametogather.gtgspringbackend.model.dto.UserDto;
import de.gametogather.gtgspringbackend.model.entity.Game;
import de.gametogather.gtgspringbackend.model.entity.User;
import de.gametogather.gtgspringbackend.model.entity.UserGame;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "role.name", target = "roleName")
    @Mapping(source = "userGames", target = "games")
    UserDto toDto(User user);

    GameDto toDto(Game game);

    default GameDto unwrapUserGame(UserGame userGame) {
        if (userGame == null || userGame.getGame() == null) {
            return null;
        }
        return toDto(userGame.getGame());
    }
}

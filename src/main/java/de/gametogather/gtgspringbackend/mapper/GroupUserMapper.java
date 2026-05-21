package de.gametogather.gtgspringbackend.mapper;

import de.gametogather.gtgspringbackend.model.dto.GroupUserDto;
import de.gametogather.gtgspringbackend.model.entity.GroupUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = GameLookupMapper.class)
public interface GroupUserMapper {
    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "user.userGames", target = "ownedGames")
    GroupUserDto toDto(GroupUser groupUser);
}

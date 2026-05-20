package de.gametogather.gtgspringbackend.mapper;

import de.gametogather.gtgspringbackend.model.dto.MeetUserDto;
import de.gametogather.gtgspringbackend.model.entity.MeetUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, MeetUserVoteMapper.class})
public interface MeetUserMapper {
    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "user", target = "User")
    MeetUserDto toDto(MeetUser meetUser);

    MeetUser toEntity(MeetUserDto meetUserDto);
}

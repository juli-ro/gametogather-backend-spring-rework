package de.gametogather.gtgspringbackend.mapper;

import de.gametogather.gtgspringbackend.model.dto.GroupDto;
import de.gametogather.gtgspringbackend.model.entity.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    GroupDto groupToGroupDto(Group group);
}

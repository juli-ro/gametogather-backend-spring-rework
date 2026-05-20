package de.gametogather.gtgspringbackend.mapper;

import de.gametogather.gtgspringbackend.model.dto.MeetDateSuggestionDto;
import de.gametogather.gtgspringbackend.model.entity.MeetDateSuggestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MeetDateSuggestionMapper {
    @Mapping(source = "meet.id", target = "meetId")
    MeetDateSuggestionDto toDto(MeetDateSuggestion meetDateSuggestion);

    @Mapping(target = "meet", ignore = true)
    MeetDateSuggestion toEntity(MeetDateSuggestionDto meetDateSuggestionDto);
}

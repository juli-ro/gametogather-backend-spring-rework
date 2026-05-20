package de.gametogather.gtgspringbackend.mapper;

import de.gametogather.gtgspringbackend.model.dto.MeetUserVoteDto;
import de.gametogather.gtgspringbackend.model.entity.MeetUserVote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeetUserVoteMapper {
    MeetUserVoteDto toDto(MeetUserVote meetUserVote);

    MeetUserVote toEntity(MeetUserVoteDto meetUserVoteDto);
}

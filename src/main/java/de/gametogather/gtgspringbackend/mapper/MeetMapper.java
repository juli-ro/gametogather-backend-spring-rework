package de.gametogather.gtgspringbackend.mapper;

import de.gametogather.gtgspringbackend.model.dto.ActivityDto;
import de.gametogather.gtgspringbackend.model.dto.MeetDateSuggestionDto;
import de.gametogather.gtgspringbackend.model.dto.MeetDto;
import de.gametogather.gtgspringbackend.model.dto.MeetUserDto;
import de.gametogather.gtgspringbackend.model.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MeetMapper {

    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "meetActivities", target = "activities")
    MeetDto toDto(Meet meet);

    ActivityDto toDto(Activity activity);
    MeetDateSuggestionDto toDto(MeetDateSuggestion meetDateSuggestion);
    MeetUserDto toDto(MeetUser meetUser);

    default ActivityDto unwrapMeetActivity(MeetActivity meetActivity) {
        if (meetActivity == null || meetActivity.getActivity() == null) {
            return null;
        }
        // Grab the actual activity and map it
        return toDto(meetActivity.getActivity());
    }
}

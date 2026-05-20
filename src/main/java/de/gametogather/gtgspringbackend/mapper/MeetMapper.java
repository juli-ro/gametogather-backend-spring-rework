package de.gametogather.gtgspringbackend.mapper;

import de.gametogather.gtgspringbackend.model.dto.ActivityDto;
import de.gametogather.gtgspringbackend.model.dto.MeetDto;
import de.gametogather.gtgspringbackend.model.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MeetDateSuggestionMapper.class, MeetUserMapper.class})
public interface MeetMapper {

    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "meetActivities", target = "activities")
    MeetDto toDto(Meet meet);

    ActivityDto toDto(Activity activity);

    default ActivityDto unwrapMeetActivity(MeetActivity meetActivity) {
        if (meetActivity == null || meetActivity.getActivity() == null) {
            return null;
        }
        // Grab the actual activity and map it
        return toDto(meetActivity.getActivity());
    }

    @Mapping(target = "group", ignore = true)
    @Mapping(target = "lastNotificationSentAt", ignore = true)
    @Mapping(target = "meetActivities", ignore = true)
    @Mapping(target = "meetDateSuggestions", ignore = true)
    @Mapping(target = "meetUsers", ignore = true)
    Meet toEntity(MeetDto meetDto);
}

package de.gametogather.gtgspringbackend.service;

import de.gametogather.gtgspringbackend.mapper.MeetDateSuggestionMapper;
import de.gametogather.gtgspringbackend.mapper.MeetMapper;
import de.gametogather.gtgspringbackend.mapper.MeetUserMapper;
import de.gametogather.gtgspringbackend.model.dto.MeetDateSuggestionDto;
import de.gametogather.gtgspringbackend.model.dto.MeetDto;
import de.gametogather.gtgspringbackend.model.dto.MeetUserDto;
import de.gametogather.gtgspringbackend.model.entity.Group;
import de.gametogather.gtgspringbackend.model.entity.Meet;
import de.gametogather.gtgspringbackend.model.entity.MeetDateSuggestion;
import de.gametogather.gtgspringbackend.model.entity.MeetUser;
import de.gametogather.gtgspringbackend.repository.GroupRepository;
import de.gametogather.gtgspringbackend.repository.MeetDateSuggestionRepository;
import de.gametogather.gtgspringbackend.repository.MeetRepository;
import de.gametogather.gtgspringbackend.repository.MeetUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MeetService {
    private final MeetRepository meetRepository;
    private final GroupRepository groupRepository;
    private final MeetUserRepository meetUserRepository;
    private final MeetDateSuggestionRepository meetDateSuggestionRepository;
    private final MeetMapper meetMapper;
    private final MeetDateSuggestionMapper meetDateSuggestionMapper;
    private final MeetUserMapper meetUserMapper;

    public List<MeetDto> getAllMeets() {
        return meetRepository.findAll().stream()
                .map(meetMapper::toDto)
                .toList();
    }

    public Optional<MeetDto> getMeetById(UUID id) {
        return meetRepository.findByIdWithDetails(id).map(meetMapper::toDto);
    }

    //Todo: every meet is probably a group meet so this might be obsolete (see controller)
//    public MeetDto createMeet(MeetDto meetDto) {
//        Meet meet = meetMapper.toEntity(meetDto);
//        Meet updatedMeet = meetRepository.save(meet);
//        return meetMapper.toDto(updatedMeet);
//    }

    public void deleteMeet(UUID id) {
        meetRepository.deleteById(id);
    }

    public List<MeetDto> getAllGroupMeets(UUID groupId) {
        return meetRepository.findByGroup_Id(groupId).stream()
                .map(meetMapper::toDto)
                .toList();
    }

    public List<MeetDto> getAllUserMeets(UUID userId) {
        return meetRepository.findByUserId(userId).stream()
                .map(meetMapper::toDto)
                .toList();
    }

    public List<MeetDto> getActiveGroupMeets(UUID groupId) {
        return meetRepository.findActiveMeets(groupId).stream()
                .map(meetMapper::toDto)
                .toList();
    }

    public UUID createGroupMeet(UUID groupId) {
        Group meetGroup = groupRepository.findByIdWithGroupUsers(groupId)
                .orElseThrow(() -> new IllegalArgumentException("No group with id " + groupId));

        Meet newMeet = Meet.builder()
                .meetType("Standard")
                .name(String.format("new %s meet", meetGroup.getName()))
                .group(meetGroup)
                .lastNotificationSentAt(Instant.now())
                .build();

        Meet updatedMeet = meetRepository.save(newMeet);

        for (var groupUser : meetGroup.getGroupUsers()) {
            meetUserRepository.save(MeetUser.builder()
                    .meet(newMeet)
                    .user(groupUser.getUser())
                    .build());
        }
        return updatedMeet.getId();
    }

    public MeetDateSuggestionDto addDate(MeetDateSuggestionDto meetDateSuggestionDto) {

        if (meetDateSuggestionRepository.existsByMeet_IdAndDate(meetDateSuggestionDto.meetId(), meetDateSuggestionDto.date())) {
            throw new IllegalArgumentException("Date already exists");
        }

        MeetDateSuggestion meetDateSuggestion = meetDateSuggestionMapper.toEntity(meetDateSuggestionDto);

        Meet meet = meetRepository.getReferenceById(meetDateSuggestionDto.meetId());
        meetDateSuggestion.setMeet(meet);
        meetDateSuggestionRepository.save(meetDateSuggestion);

        return meetDateSuggestionMapper.toDto(meetDateSuggestion);
    }


    public MeetDateSuggestionDto selectDate(UUID meetDateId) {
        MeetDateSuggestion meetDateSuggestion = meetDateSuggestionRepository.findById(meetDateId)
                .orElseThrow(() -> new IllegalArgumentException("No such meet id " + meetDateId));
        meetDateSuggestion.setIsChosenDate(!meetDateSuggestion.getIsChosenDate());
        meetDateSuggestionRepository.save(meetDateSuggestion);
        return meetDateSuggestionMapper.toDto(meetDateSuggestion);
    }

    public Optional<MeetUserDto> getCurrentMeetUser(UUID meetId, UUID userId) {
        return meetUserRepository.findByMeet_IdAndUser_Id(meetId, userId)
                .map(meetUserMapper::toDto);
    }

    public Boolean updateParticipant(UUID meetUserId, boolean isParticipating) {
        MeetUser meetUser = meetUserRepository.findById(meetUserId).orElseThrow(() -> new IllegalArgumentException("No such meet user " + meetUserId));

        meetUser.setIsParticipating(isParticipating);
        meetUserRepository.save(meetUser);

        return isParticipating;
    }
}

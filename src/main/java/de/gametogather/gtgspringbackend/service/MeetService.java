package de.gametogather.gtgspringbackend.service;

import de.gametogather.gtgspringbackend.mapper.MeetMapper;
import de.gametogather.gtgspringbackend.model.dto.GroupDto;
import de.gametogather.gtgspringbackend.model.dto.MeetDto;
import de.gametogather.gtgspringbackend.model.entity.Group;
import de.gametogather.gtgspringbackend.model.entity.Meet;
import de.gametogather.gtgspringbackend.model.entity.MeetUser;
import de.gametogather.gtgspringbackend.repository.GroupRepository;
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
    private final MeetMapper meetMapper;

    public List<MeetDto> getAllMeets() {
        return meetRepository.findAll().stream()
                .map(meetMapper::toDto)
                .toList();
    }

    public Optional<MeetDto> getMeetById(UUID id) {
        return meetRepository.findByIdWithDetails(id).map(meetMapper::toDto);
    }

    public MeetDto createMeet(MeetDto meetDto) {
        Meet meet = meetMapper.toEntity(meetDto);
        Meet updatedMeet = meetRepository.save(meet);
        return meetMapper.toDto(updatedMeet);
    }

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

        for (var groupUser : meetGroup.getGroupUsers()){
            meetUserRepository.save(MeetUser.builder()
                    .meet(newMeet)
                    .user(groupUser.getUser())
                    .build());
        }
        return updatedMeet.getId();
    }
}

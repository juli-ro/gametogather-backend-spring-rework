package de.gametogather.gtgspringbackend.service;

import de.gametogather.gtgspringbackend.mapper.MeetMapper;
import de.gametogather.gtgspringbackend.model.dto.MeetDto;
import de.gametogather.gtgspringbackend.model.entity.Meet;
import de.gametogather.gtgspringbackend.repository.MeetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetService {
    private final MeetRepository meetRepository;
    private final MeetMapper meetMapper;

    public List<MeetDto> getAllMeets() {
        return meetRepository.findAll().stream()
                .map(meetMapper::toDto)
                .toList();
    }

    public MeetDto createMeet(MeetDto meetDto) {
        Meet meet = meetMapper.toEntity(meetDto);
        Meet updatedMeet = meetRepository.save(meet);
        return meetMapper.toDto(updatedMeet);
    }
}

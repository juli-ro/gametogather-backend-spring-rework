package de.gametogather.gtgspringbackend.service;

import de.gametogather.gtgspringbackend.repository.MeetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeetService {
    private final MeetRepository meetRepository;
}

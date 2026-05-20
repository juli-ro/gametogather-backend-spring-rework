package de.gametogather.gtgspringbackend.controller;

import de.gametogather.gtgspringbackend.model.dto.MeetDto;
import de.gametogather.gtgspringbackend.service.MeetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meet")
@RequiredArgsConstructor
public class MeetController {
    private final MeetService meetService;

    @GetMapping
    public List<MeetDto> GetAllMeets() {
        return meetService.getAllMeets();
    }

    @PostMapping
    public ResponseEntity<MeetDto> CreateMeet(@RequestBody MeetDto meetDto) {
        MeetDto savedDto = meetService.createMeet(meetDto);
        return ResponseEntity.ok(savedDto);
    }
}

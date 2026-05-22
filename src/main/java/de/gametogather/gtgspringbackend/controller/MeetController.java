package de.gametogather.gtgspringbackend.controller;

import de.gametogather.gtgspringbackend.model.dto.MeetDto;
import de.gametogather.gtgspringbackend.service.MeetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/meet")
@RequiredArgsConstructor
public class MeetController {
    private final MeetService meetService;

    @GetMapping
    public List<MeetDto> getAllMeets() {
        return meetService.getAllMeets();
    }

    @GetMapping("{meetId}")
    public ResponseEntity<MeetDto> getMeetById(@PathVariable UUID meetId) {
        return meetService.getMeetById(meetId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Todo: every meet is probably a group meet so this might be obsolete
//    @PostMapping
//    public ResponseEntity<MeetDto> createMeet(@RequestBody MeetDto meetDto) {
//        MeetDto savedDto = meetService.createMeet(meetDto);
//        return ResponseEntity.ok(savedDto);
//    }

    @DeleteMapping("/{meetId}")
    public ResponseEntity<Void> deleteMeet(@PathVariable UUID meetId) {
        try {
            meetService.deleteMeet(meetId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    //Todo: rename url in frontend
    @GetMapping("/group-meets/{groupId}")
    public List<MeetDto> getAllGroupMeets(@PathVariable UUID groupId) {
        return meetService.getAllGroupMeets(groupId);
    }

    //Todo: rename url in frontend
    @GetMapping("/user-meets")
    public List<MeetDto> getAllUserMeets(@AuthenticationPrincipal Jwt jwt){
        UUID userId = UUID.fromString(jwt.getSubject());
        return meetService.getAllUserMeets(userId);
    }

    //Todo: rename url in frontend
    @GetMapping("/active-meets/{groupId}")
    public List<MeetDto> getActiveGroupMeets(@PathVariable UUID groupId) {
        return meetService.getActiveGroupMeets(groupId);
    }

    @PostMapping("/group-meets/{groupId}")
    public ResponseEntity<UUID> createGroupMeet(@PathVariable UUID groupId) {
        UUID newId = meetService.createGroupMeet(groupId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newId);
    }
}

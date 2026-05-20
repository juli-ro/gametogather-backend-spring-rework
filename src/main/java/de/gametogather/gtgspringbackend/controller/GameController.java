package de.gametogather.gtgspringbackend.controller;

import de.gametogather.gtgspringbackend.model.dto.GameDto;
import de.gametogather.gtgspringbackend.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping
    public List<GameDto> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable UUID id) {
        return gameService.getGameById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Todo: see add User Game. URL needs to be changed in frontend
    @GetMapping("/user-games")
    public ResponseEntity<List<GameDto>> getAllGamesByUserId(@AuthenticationPrincipal Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getSubject());

        return ResponseEntity.ok(gameService.getAllGamesByUserId(userId));
    }

    //Todo: URL needs to be changed in the frontend
    @GetMapping("/group-games/{groupId}")
    public ResponseEntity<List<GameDto>> getGroupGamesByGroupId(@PathVariable UUID groupId) {
        return ResponseEntity.ok(gameService.getGroupGamesByGroupId(groupId));
    }

    @PostMapping
    public ResponseEntity<GameDto> createGame(@RequestBody GameDto gameDto) {
        GameDto savedDto = gameService.createGame(gameDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }

    @PutMapping
    public ResponseEntity<GameDto> updateGame(@RequestBody GameDto gameDto) {
        GameDto savedDto = gameService.updateGame(gameDto);
        return ResponseEntity.ok(savedDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteGame(@RequestBody UUID gameId) {
        try {
            gameService.deleteGame(gameId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Todo: URL needs to be changed in the frontend
    @PostMapping("/user-games")
    public ResponseEntity<Void> addUserGame(@RequestBody GameDto dto, @AuthenticationPrincipal Jwt jwt) {
        try {
            UUID userId = UUID.fromString(jwt.getSubject());
            gameService.addUserGame(dto.id(), userId);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/user-games/{gameId}")
    public ResponseEntity<Void> deleteUserGame(@PathVariable UUID gameId, @AuthenticationPrincipal Jwt jwt) {
        try {
            UUID userId = UUID.fromString(jwt.getSubject());
            gameService.deleteUserGame(gameId, userId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }

    }

}

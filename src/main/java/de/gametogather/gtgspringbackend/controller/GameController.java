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

    //Todo: url needs to be changed in frontend
    @PutMapping("/{gameId}")
    public ResponseEntity<GameDto> updateGame(@PathVariable UUID gameId, @RequestBody GameDto gameDto) {
        if(!gameId.equals(gameDto.id())){
            return ResponseEntity.badRequest().build();
        }
        GameDto savedDto = gameService.updateGame(gameDto);
        return ResponseEntity.ok(savedDto);
    }

    //Todo: url needs to be changed in frontend
    @DeleteMapping("/{gameId}")
    public ResponseEntity<Void> deleteGame(@PathVariable UUID gameId) {
        try {
            gameService.deleteGame(gameId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Todo: URL needs to be changed in the frontend
    @PostMapping("/user-games/{gameId}")
    public ResponseEntity<Void> addUserGame(@PathVariable UUID gameId, @AuthenticationPrincipal Jwt jwt) {
        try {
            UUID userId = UUID.fromString(jwt.getSubject());
            gameService.addUserGame(gameId, userId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    //Todo: URL needs to be changed in the frontend
    @DeleteMapping("/user-games/{gameId}")
    public ResponseEntity<Void> deleteUserGame(@PathVariable UUID gameId, @AuthenticationPrincipal Jwt jwt) {
        try {
            UUID userId = UUID.fromString(jwt.getSubject());
            gameService.deleteUserGame(gameId, userId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }

    }

}

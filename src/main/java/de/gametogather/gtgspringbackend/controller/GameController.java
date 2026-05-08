package de.gametogather.gtgspringbackend.controller;

import de.gametogather.gtgspringbackend.model.dto.GameDto;
import de.gametogather.gtgspringbackend.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/games")
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

    @PostMapping
    public ResponseEntity<GameDto> createGame(@RequestBody GameDto gameDto) {

        GameDto savedDto = gameService.createGame(gameDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }

    //Todo: Have to create jwt implementation first
    //Todo: URL needs to be changed in the frontend
//    @PostMapping("/user-games")
//    public ResponseEntity<Void> addUserGame(@RequestBody GameDto dto, @AuthenticationPrincipal Jwt jwt) {
//        try {
//            UUID userId = UUID.fromString(jwt.getSubject());
//            gameService.addUserGame(dto.id(), userId);
//            return ResponseEntity.ok().build();
//        } catch (IllegalStateException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        }
//    }

}

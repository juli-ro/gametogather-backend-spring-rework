package de.gametogather.gtgspringbackend.service;


import de.gametogather.gtgspringbackend.mapper.GameMapper;
import de.gametogather.gtgspringbackend.model.dto.GameDto;
import de.gametogather.gtgspringbackend.model.entity.Game;
import de.gametogather.gtgspringbackend.model.entity.User;
import de.gametogather.gtgspringbackend.model.entity.UserGame;
import de.gametogather.gtgspringbackend.repository.GameRepository;
import de.gametogather.gtgspringbackend.repository.UserGameRepository;
import de.gametogather.gtgspringbackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final UserGameRepository userGameRepository;
    private final GameMapper gameMapper;

    public List<GameDto> getAllGames() {
        return gameRepository.findAll().stream()
                .map(gameMapper::toDto)
                .toList();
    }

    public Optional<GameDto> getGameById(UUID id) {
        return gameRepository.findById(id).map(gameMapper::toDto);
    }

    public List<GameDto> getAllGamesByUserId(UUID userId) {
        return gameRepository.FindAllByUserId(userId).stream()
                .map(gameMapper::toDto)
                .toList();
    }

    @Transactional
    public GameDto createGame(GameDto gameDto) {
        Game game = gameMapper.toEntity(gameDto);
        Game UpdatedGame = gameRepository.save(game);
        return gameMapper.toDto(UpdatedGame);
    }

    @Transactional
    public GameDto updateGame(GameDto gameDto) {
        Game game = gameMapper.toEntity(gameDto);
        Game UpdatedGame = gameRepository.save(game);
        return gameMapper.toDto(UpdatedGame);
    }

    @Transactional
    public void addUserGame(UUID gameId, UUID userId) {
        if (userGameRepository.existsByUserIdAndGameId(userId, gameId)) {
            throw new IllegalStateException("User already has this game");
        }

        User userProxy = userRepository.getReferenceById(userId);
        Game gameProxy = gameRepository.getReferenceById(gameId);

        UserGame userGame = UserGame.builder()
                .user(userProxy)
                .game(gameProxy)
                .build();

        userGameRepository.save(userGame);
    }
}

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
        return gameRepository.findAllByUserId(userId).stream()
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
        //The following check is needed in case a game is soft deleted
        Optional<UserGame> existingLink = userGameRepository.findByUserIdAndGameId(userId, gameId);
        if (existingLink.isPresent()) {
            UserGame link = existingLink.get();
            if (link.isDeleted()) {
                link.setDeleted(false);
                userGameRepository.save(link);
                return;
            } else {
                throw new IllegalStateException("User already has this game");
            }
        }

        User userProxy = userRepository.getReferenceById(userId);
        Game gameProxy = gameRepository.getReferenceById(gameId);

        UserGame userGame = UserGame.builder()
                .user(userProxy)
                .game(gameProxy)
                .build();

        userGameRepository.save(userGame);
    }

    @Transactional
    public void deleteUserGame(UUID gameId, UUID userId) {
        UserGame userGame = userGameRepository.findByUserIdAndGameId(userId, gameId)
                .orElseThrow(() -> new IllegalStateException("Entry not found"));
        userGameRepository.delete(userGame);
    }

    @Transactional
    public void deleteGame(UUID gameId) {
        gameRepository.deleteById(gameId);
    }

    public List<GameDto> getGroupGamesByGroupId(UUID groupId) {
        return gameRepository.findDistinctGamesByGroupId(groupId).stream()
                .map(gameMapper::toDto)
                .toList();
    }
}

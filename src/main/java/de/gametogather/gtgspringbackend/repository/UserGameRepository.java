package de.gametogather.gtgspringbackend.repository;

import de.gametogather.gtgspringbackend.model.entity.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserGameRepository extends JpaRepository<UserGame, UUID> {
    boolean existsByUserIdAndGameId(UUID userId, UUID gameId);
    @Query(value = "SELECT * FROM user_games WHERE user_id = :userId AND game_id = :gameId", nativeQuery = true)
    Optional<UserGame> findByUserIdAndGameId(UUID userId, UUID gameId);
}

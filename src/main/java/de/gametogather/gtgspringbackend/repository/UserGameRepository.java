package de.gametogather.gtgspringbackend.repository;

import de.gametogather.gtgspringbackend.model.entity.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserGameRepository extends JpaRepository<UserGame, UUID> {
    boolean existsByUserIdAndGameId(UUID userId, UUID gameId);
    @Query(value = "SELECT * FROM UserGames WHERE UserId = :userId AND GameId = :gameId", nativeQuery = true)
    Optional<UserGame> findByUserIdAndGameId(@Param("userId") UUID userId, @Param("gameId") UUID gameId);
}

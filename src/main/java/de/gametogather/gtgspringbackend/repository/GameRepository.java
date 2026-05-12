package de.gametogather.gtgspringbackend.repository;

import de.gametogather.gtgspringbackend.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface GameRepository extends JpaRepository<Game, UUID> {
    @Query("SELECT ug.game FROM UserGame ug WHERE ug.user.id = :userId")
    List<Game> FindAllByUserId(@Param("userId") UUID userId);

    @Query("SELECT DISTINCT ug.game FROM GroupUser gu JOIN gu.user u JOIN u.userGames ug WHERE gu.group.id = :groupId")
    List<Game> findDistinctGamesByGroupId(@Param("groupId") UUID groupId);


}

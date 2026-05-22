package de.gametogather.gtgspringbackend.repository;

import de.gametogather.gtgspringbackend.model.entity.Meet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MeetRepository extends JpaRepository<Meet, UUID> {
    @Query("SELECT m FROM Meet m LEFT JOIN FETCH m.meetDateSuggestions mds WHERE m.group.id = :groupId")
    List<Meet> findByGroup_Id(@Param("groupId") UUID groupId);

    @Query("SELECT m FROM Meet m JOIN  m.meetUsers mu JOIN mu.user u WHERE u.id = :userId")
    List<Meet> findByUserId(@Param("userId") UUID userID);

    @Query("SELECT m FROM Meet m LEFT JOIN FETCH m.meetDateSuggestions mds WHERE m.group.id = :groupId AND ((mds.date >= CURRENT_TIMESTAMP AND mds.isChosenDate = TRUE) OR (mds.id IS NULL))")
    List<Meet> findActiveMeets(@Param("groupId") UUID groupId);

    @Query("SELECT m FROM Meet m LEFT JOIN FETCH m.meetDateSuggestions mds LEFT JOIN FETCH m.meetUsers mu LEFT JOIN FETCH mu.user LEFT JOIN FETCH mu.meetUserVotes WHERE m.id = :meetId")
    Optional<Meet> findByIdWithDetails(@Param("meetId") UUID meetId);
}
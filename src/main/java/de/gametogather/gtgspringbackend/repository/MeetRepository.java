package de.gametogather.gtgspringbackend.repository;

import de.gametogather.gtgspringbackend.model.entity.Meet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MeetRepository extends JpaRepository<Meet, UUID> {
    List<Meet> findByGroup_Id(UUID groupId);

    @Query("SELECT m FROM Meet m JOIN  m.meetUsers mu JOIN mu.user u WHERE u.id = :userId")
    List<Meet> findByUserId(@Param("userId") UUID userID);

    @Query("SELECT m FROM Meet m LEFT JOIN m.meetDateSuggestions mds WHERE mds.date >= CURRENT_TIMESTAMP AND m.group.id = :groupId AND mds.isChosenDate = TRUE")
    List<Meet> findActiveMeets(@Param("groupId") UUID groupId);
}
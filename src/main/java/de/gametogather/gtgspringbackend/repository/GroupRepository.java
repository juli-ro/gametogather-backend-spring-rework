package de.gametogather.gtgspringbackend.repository;

import de.gametogather.gtgspringbackend.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {
    @Query("SELECT g FROM Group g LEFT JOIN FETCH g.groupUsers gu LEFT JOIN FETCH gu.user WHERE g.id = :id")
    Optional<Group> findByIdWithGroupUsers(@Param("id") UUID id);
}

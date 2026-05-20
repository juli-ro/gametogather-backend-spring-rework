package de.gametogather.gtgspringbackend.repository;

import de.gametogather.gtgspringbackend.model.entity.MeetUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MeetUserRepository extends JpaRepository<MeetUser, UUID> {
    Optional<MeetUser> findByMeet_IdAndUser_Id(UUID meetId, UUID userId);
    List<MeetUser> findByMeet_Id(UUID meetId);
}

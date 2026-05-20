package de.gametogather.gtgspringbackend.repository;

import de.gametogather.gtgspringbackend.model.entity.MeetDateSuggestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.UUID;

public interface MeetDateSuggestionRepository extends JpaRepository<MeetDateSuggestion, UUID> {
    boolean existsByMeet_IdAndDate(UUID meetId, Instant date);
}

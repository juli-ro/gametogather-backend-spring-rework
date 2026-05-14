package de.gametogather.gtgspringbackend.repository;

import de.gametogather.gtgspringbackend.model.entity.Meet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MeetRepository extends JpaRepository<Meet, UUID> {

}

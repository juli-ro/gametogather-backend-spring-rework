package de.gametogather.gtgspringbackend.repository;

import de.gametogather.gtgspringbackend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}

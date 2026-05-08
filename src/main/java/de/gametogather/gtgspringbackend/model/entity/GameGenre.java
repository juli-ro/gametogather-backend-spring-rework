package de.gametogather.gtgspringbackend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "GameGenres")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE GameGenres SET IsDeleted = true, DeletedAt = NOW() WHERE Id=?")
public class GameGenre extends ModelBase {

    @Size(max = 255)
    @NotNull
    @Column(nullable = false)
    private String name;

    @OneToMany
    @JoinColumn(name = "GenreId")
    @Builder.Default
    private Set<Game> games = new LinkedHashSet<>();}
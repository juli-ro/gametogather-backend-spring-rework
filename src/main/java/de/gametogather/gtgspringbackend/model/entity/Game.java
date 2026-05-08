package de.gametogather.gtgspringbackend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "Games")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE Games SET IsDeleted = true, DeletedAt = NOW() WHERE Id=?")
public class Game extends ModelBase {

    @Size(max = 255)
    @Column(nullable = false)
    private String name;

    private int minPlayerNumber;
    private int maxPlayerNumber;
    private int playTime;
    private int yearPublished;
    private int minAge;

    @Column(nullable = false)
    @Builder.Default
    private boolean isVerified = false;

    // Navigation Properties

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GenreId")
    private GameGenre gameGenre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ImageId")
    private Image image;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<UserGame> userGames = new LinkedHashSet<>();
}

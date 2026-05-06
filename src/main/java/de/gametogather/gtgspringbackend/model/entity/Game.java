package de.gametogather.gtgspringbackend.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE Games SET IsDeleted = true, DeletedAt = NOW() WHERE Id=?")
@SQLRestriction("IsDeleted = false")
public class Game extends ModelBase {

    @Column(length = 255)
    private String name;

    private int minPlayerNumber;
    private int maxPlayerNumber;
    private int playTime;
    private int yearPublished;
    private int minAge;
    private boolean isVerified;

    // Navigation Properties ---

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GenreId")
    private GameGenre gameGenre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ImageId")
    private Image image;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserGame> userGames;
}

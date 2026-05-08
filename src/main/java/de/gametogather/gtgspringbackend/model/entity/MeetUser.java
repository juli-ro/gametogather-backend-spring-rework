package de.gametogather.gtgspringbackend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "MeetUsers")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE MeetUsers SET IsDeleted = true, DeletedAt = NOW() WHERE Id=?")
public class MeetUser extends ModelBase {
    @NotNull
    @Column(nullable = false)
    @Builder.Default
    private Boolean isHost = false;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "UserId", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "MeetId", nullable = false)
    private Meet meet;

    @NotNull
    @Column(name = "IsParticipating", nullable = false)
    @Builder.Default
    private Boolean isParticipating = false;

    @OneToMany(mappedBy = "meetUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Assignment> assignments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "meetUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Food> foods = new LinkedHashSet<>();

    @OneToMany(mappedBy = "meetUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MeetUserMessage> meetUserMessages = new LinkedHashSet<>();

    @OneToMany(mappedBy = "meetUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MeetUserVote> meetUserVotes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "meetUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Movie> movies = new LinkedHashSet<>();


}
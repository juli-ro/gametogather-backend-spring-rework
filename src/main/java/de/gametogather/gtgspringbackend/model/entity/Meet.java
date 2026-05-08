package de.gametogather.gtgspringbackend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "Meets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE Meets SET IsDeleted = true, DeletedAt = NOW() WHERE Id=?")
public class Meet extends ModelBase {

    @Size(max = 255)
    @NotNull
    private String meetType;

    @NotNull
    @Builder.Default
    private Boolean hasMovies = false;

    @NotNull
    @Builder.Default
    private Boolean hasGames = false;


    @Size(max = 255)
    @NotNull
    private String name;

    @NotNull
    @Column(nullable = false)
    private Instant lastNotificationSentAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "GroupId", nullable = false)
    private Group group;

    @OneToMany(mappedBy = "meet", cascade =  CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MeetActivity> meetActivities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "meet", cascade =  CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MeetDateSuggestion> meetDateSuggestions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "meet", cascade =  CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MeetUser> meetUsers = new LinkedHashSet<>();


}
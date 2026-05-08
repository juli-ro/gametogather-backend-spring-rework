package de.gametogather.gtgspringbackend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "Activities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE Activities SET IsDeleted = true, DeletedAt = NOW() WHERE Id=?")
public class Activity extends ModelBase{

    @Size(max = 255)
    @NotNull
    @Column(nullable = false)
    private String name;

    @Size(max = 10000)
    @NotNull
    @Column(columnDefinition = "TEXT", nullable = false)
    private String activityDescription;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GroupId", nullable = false) // Add nullable = false if an Activity MUST have a Group
    private Group group;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MeetActivity> meetActivities = new LinkedHashSet<>();
}

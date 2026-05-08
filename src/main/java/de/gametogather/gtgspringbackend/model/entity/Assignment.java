package de.gametogather.gtgspringbackend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "Assignments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE Assignments SET IsDeleted = true, DeletedAt = NOW() WHERE Id=?")
public class Assignment extends ModelBase{
    @Size(max = 255)
    @NotNull
    @Column(name = "Name", nullable = false)
    private String name;

    @Size(max = 10000)
    @NotNull
    @Column(columnDefinition = "TEXT", nullable = false)
    private String assignmentDescription;

    @NotNull
    @Column(nullable = false)
    @Builder.Default
    private boolean isDone = false;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "MeetUserId", nullable = false)
    private MeetUser meetUser;
}
package de.gametogather.gtgspringbackend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "MeetDateSuggestions")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE Meets SET IsDeleted = true, DeletedAt = NOW() WHERE Id=?")
public class MeetDateSuggestion extends ModelBase {
    @NotNull
    @Column(nullable = false)
    private Instant date;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "MeetId", nullable = false)
    private Meet meet;

    @NotNull
    @Column(nullable = false)
    @Builder.Default
    private Boolean isChosenDate = false;
}
package de.gametogather.gtgspringbackend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;

@Getter
@Setter
@Entity
@Table(name = "MeetUserMessages")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE MeetUserMessages SET IsDeleted = true, DeletedAt = NOW() WHERE Id=?")
public class MeetUserMessage extends ModelBase {
    @NotNull
    @Size(max = 20000)
    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "MeetUserId", nullable = false)
    private MeetUser meetUser;


}
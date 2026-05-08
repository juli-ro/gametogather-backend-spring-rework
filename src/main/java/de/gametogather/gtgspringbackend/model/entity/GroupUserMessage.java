package de.gametogather.gtgspringbackend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@Entity
@Table(name = "GroupUserMessages")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE GroupUserMessages SET IsDeleted = true, DeletedAt = NOW() WHERE Id=?")
public class GroupUserMessage extends ModelBase {
    @NotNull
    @Size(max = 20000)
    @Column(columnDefinition = "TEXT", name = "Message", nullable = false)
    private String message;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "GroupUserId", nullable = false)
    private GroupUser groupUser;
}
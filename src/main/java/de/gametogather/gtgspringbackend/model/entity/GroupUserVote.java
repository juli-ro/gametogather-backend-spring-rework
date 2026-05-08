package de.gametogather.gtgspringbackend.model.entity;

import de.gametogather.gtgspringbackend.model.enums.VotableItemType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "GroupUserVotes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE GroupUserVotes SET IsDeleted = true, DeletedAt = NOW() WHERE Id=?")
public class GroupUserVote extends ModelBase {
    @NotNull
    @Column(nullable = false)
    private Double rating;

    //Todo: Because of the polymorphic association the deleting of associated items have to be handled in the service
    // Pleas put a reference here once this is done
    @NotNull
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(columnDefinition = "CHAR(36)")
    private UUID votableItemId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VotableItemType votableItemType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "GroupUserId", nullable = false)
    private GroupUser groupUser;


}
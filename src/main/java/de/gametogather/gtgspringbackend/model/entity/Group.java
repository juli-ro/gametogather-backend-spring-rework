package de.gametogather.gtgspringbackend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Groups")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE Groups SET IsDeleted = true, DeletedAt = NOW() WHERE Id=?")
public class Group extends ModelBase {

    @Size(max = 255)
    @NotNull
    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy = "group",  fetch = FetchType.LAZY)
    private GroupSetting groupSetting;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<GroupUser> groupUsers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Activity> activities = new LinkedHashSet<>();

    //Todo: check if this is obsolete - this may be an old implementation idea that only groups can created genres for themselves
//    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    @Builder.Default
//    private Set<GameGenre> gameGenres = new LinkedHashSet<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Meet>  meets = new LinkedHashSet<>();
}
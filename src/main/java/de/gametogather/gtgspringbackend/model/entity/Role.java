package de.gametogather.gtgspringbackend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Roles")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE Roles SET IsDeleted = true, DeletedAt = NOW() WHERE Id=?")

public class Role extends ModelBase {
    @Size(max = 255)
    @NotNull
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "role", cascade =  CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<User> users = new LinkedHashSet<>();


}
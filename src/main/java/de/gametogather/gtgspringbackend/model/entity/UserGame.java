package de.gametogather.gtgspringbackend.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class UserGame extends ModelBase {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GameId")
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId")
    private User user;}
package de.gametogather.gtgspringbackend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class User extends ModelBase {

    //TODO [Reverse Engineering] generate columns from DB
}
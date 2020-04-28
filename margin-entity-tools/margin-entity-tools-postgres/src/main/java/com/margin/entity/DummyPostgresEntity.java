package com.margin.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dummy")
@Getter
@Setter
public class DummyPostgresEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;
}

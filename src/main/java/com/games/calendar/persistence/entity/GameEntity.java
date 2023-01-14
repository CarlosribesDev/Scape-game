package com.games.calendar.persistence.entity;

import com.games.calendar.model.constants.GameName;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="game")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="name",nullable = false)
    private GameName name;

    @Column(name="price",nullable = false)
    private Float price;

    @Column(name="title")
    private String title;

    @Lob
    @Column(name="description")
    private String description;

    @Column(name="duration")
    private Integer duration;

    @OneToMany(mappedBy = "game")
    private Set<BookingEntity> bookings;
}

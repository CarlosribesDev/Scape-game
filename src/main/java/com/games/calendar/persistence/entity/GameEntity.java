package com.games.calendar.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="service")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="duration" ,nullable = false)
    private Integer duration;

    @BatchSize(size = 1000)
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "game",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookingEntity> bookings;


}

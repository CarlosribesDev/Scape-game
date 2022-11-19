package com.games.calendar.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="hour")
public class HourEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    Long id;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "schedule_hour",
            joinColumns = @JoinColumn(name = "hour_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_id")
    )
    private Set<ScheduleEntity> schedules;

    @Column(name="hour")
    String hour;
}

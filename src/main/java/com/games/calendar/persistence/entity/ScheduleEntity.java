package com.games.calendar.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="schedule")
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    Long id;

    @ManyToMany(mappedBy = "schedules")
    Set<HourEntity> hours;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "schedule",fetch = FetchType.LAZY,orphanRemoval = true)
    Set<DayEntity> days;

    @Column(name="name")
    String name;
}

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

    @ElementCollection
    @CollectionTable(name = "schedule_hours",
    joinColumns = @JoinColumn(name = "schedule_id"))
    @Column(name = "hour")
    Set<String> hours;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "schedule",fetch = FetchType.LAZY,orphanRemoval = true)
    Set<DayEntity> days;

    @Column(name="name")
    String name;
}

package com.games.calendar.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="day")
public class DayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @BatchSize(size = 1000)
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<BookingEntity> bookings;

    @Column(name="DAY_DATE")
    private LocalDate date;
}

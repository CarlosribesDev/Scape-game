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
@Table(name="app_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name" , nullable = false)
    private String name;

    @Column(name="surname", nullable = false)
    private String surname;

    @Column(name="email",unique = true, nullable = false)
    private String email;

    @Column(name="telephone",unique = true, nullable = false)
    private String telephone;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private Set<BookingEntity> bookings;


}

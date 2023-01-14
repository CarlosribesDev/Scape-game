package com.games.calendar.persistence.entity;

import lombok.Getter;
import lombok.Setter;

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

    @Column(name="email",unique = true)
    private String email;

    @Column(name="telephone",unique = true, nullable = false)
    private String telephone;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<BookingEntity> bookings;


}

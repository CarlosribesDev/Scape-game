package com.games.calendar.persistence.entity;

import com.games.calendar.model.RoleType;
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

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="telephone", nullable = false)
    private String telephone;

    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable = false)
    private RoleType role;

    @BatchSize(size = 1000)
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookingEntity> bookings;
}

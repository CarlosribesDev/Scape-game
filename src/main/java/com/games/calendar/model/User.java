package com.games.calendar.model;

import com.games.calendar.model.constants.RoleType;
import lombok.Data;

import java.util.Set;

@Data
public class User implements JsonModel{
    private long id;
    private String name;
    private String surname;
    private RoleType role;
    private String email;
    private String telephone;
    private Set<Booking> bookings;
}

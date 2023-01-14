package com.games.calendar.model;
import lombok.Data;

@Data
public class UserWithoutBookings implements JsonModel{
    private long id;
    private String name;
    private String surname;
    private String email;
    private String telephone;
}

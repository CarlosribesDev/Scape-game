package com.games.calendar.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Booking implements JsonModel{
    private long id;
    private String hour;
    private LocalDate date;
    private Long dayId;
    private Game game;
    private UserWithoutBookings user;
}

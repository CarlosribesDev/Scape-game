package com.games.calendar.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Booking implements JsonModel{
    private long id;
    private String hour;
    private int dayId;
    private LocalDate date;
    private Game game;
    private long userId;
}

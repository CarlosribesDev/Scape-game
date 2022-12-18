package com.games.calendar.model;

import lombok.Data;

@Data
public class Booking implements JsonModel{
    private long id;
    private String hour;
    private boolean isBusy;
    private int day_id;
    private Game game;
    private long userId;
}

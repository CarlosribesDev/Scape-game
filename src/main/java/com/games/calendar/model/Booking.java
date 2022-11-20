package com.games.calendar.model;

public class Booking implements JsonModel{
    private long id;
    private String hour;
    private boolean isBusy;
    private Day day;
    private Game game;
    private long userId;
}

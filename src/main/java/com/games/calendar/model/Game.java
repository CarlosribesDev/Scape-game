package com.games.calendar.model;

import lombok.Data;

@Data
public class Game implements JsonModel{
    private long id;
    private int duration;
    private String name;
    private String description;
    private float price;
}

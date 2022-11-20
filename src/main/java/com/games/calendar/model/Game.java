package com.games.calendar.model;

import com.games.calendar.model.constants.GameType;
import lombok.Data;

@Data
public class Game implements JsonModel{
    private long id;
    private int duration;
    private GameType type;
    private float price;
}

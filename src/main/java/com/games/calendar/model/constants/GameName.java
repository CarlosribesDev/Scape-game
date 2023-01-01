package com.games.calendar.model.constants;

import com.games.calendar.model.JsonModel;
import lombok.Getter;

@Getter
public enum GameName implements JsonModel {

    HAUNTED_HOUSE("La casa encantada"),
    THE_DEN("La guarida");

    private final String translation;

    GameName(String translation){
        this.translation = translation;
    }
}

package com.games.calendar.model;

import lombok.Data;

import java.util.Set;

@Data
public class Schedule {

    private long id;
    private String name;
    Set<String> hours;
}

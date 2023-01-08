package com.games.calendar.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Day {

    private long id;
    private LocalDate date;
    private List<Booking> bookings;
    private boolean isBusy;
}

package com.games.calendar.request;

import lombok.Data;

@Data
public class UserBookingRequest {

    private Long userId;
    private Long gameId;
}

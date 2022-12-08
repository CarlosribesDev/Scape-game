package com.games.calendar.request;

import lombok.*;

@Data
public class NewUserRequest {

    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private String telephone;
}

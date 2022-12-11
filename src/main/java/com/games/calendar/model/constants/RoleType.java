package com.games.calendar.model.constants;

import com.games.calendar.model.JsonModel;
import lombok.Getter;

@Getter
public enum RoleType implements JsonModel {

    ROLE_USER,
    ROLE_ADMIN
}

package com.games.calendar.security;

import com.games.calendar.model.constants.RoleType;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class Authority implements GrantedAuthority {

    private RoleType authority;

    @Override
    public String getAuthority() {
        return this.authority.toString();
    }
}

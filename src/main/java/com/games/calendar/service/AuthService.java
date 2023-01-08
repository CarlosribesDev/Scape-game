package com.games.calendar.service;

import com.games.calendar.persistence.entity.UserAuthEntity;
import com.games.calendar.persistence.repository.UserAuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import com.games.calendar.security.Authority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserAuthRepository userAuthRepository;

    @Override
    public UserAuthEntity loadUserByUsername(String username) throws UsernameNotFoundException {

        return userAuthRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username ".concat(username).concat(" not found")));
    }
}

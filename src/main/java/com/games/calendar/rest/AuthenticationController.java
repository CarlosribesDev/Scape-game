package com.games.calendar.rest;

import com.games.calendar.mapper.UserMapper;
import com.games.calendar.model.User;
import com.games.calendar.persistence.entity.UserAuthEntity;
import com.games.calendar.persistence.entity.UserEntity;
import com.games.calendar.request.AuthRequest;
import com.games.calendar.request.JwtResponse;
import com.games.calendar.security.JwtUtils;

import com.games.calendar.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final JwtUtils jwtUtils;

    private final UserMapper userMapper;


    @RequestMapping(value = "/authenticate" , method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        }catch (BadCredentialsException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid credentials");
        }catch (DisabledException disabledException){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User disabled");
        }

        final UserAuthEntity user = authService.loadUserByUsername(authRequest.getUsername());

        if(user != null){
            String token = jwtUtils.generateToken(user);
            return ResponseEntity.ok(new JwtResponse(token));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some error occurred");
    }

    @RequestMapping(value = "/current-user" , method = RequestMethod.GET)
    public ResponseEntity<User> getUserData(Principal principal){
        UserAuthEntity userAuth = this.authService.loadUserByUsername(principal.getName());
        UserEntity userEntity = userAuth.getUser();

        return ResponseEntity.ok(this.userMapper.entityToModel(userEntity));
    }

}

package com.games.calendar.rest;

import com.games.calendar.persistence.entity.UserAuthEntity;
import com.games.calendar.request.AuthRequest;
import com.games.calendar.security.JwtUtils;

import com.games.calendar.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest authRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        final UserAuthEntity user = authService.loadUserByUsername(authRequest.getUsername());

        if(user != null){
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }
        return ResponseEntity.status(400).body("Some error occurred");
    }

//    @PostMapping("/generate-token")
//    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest){
//        try{
//            this.authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
//        }catch (Exception e){
//            e.printStackTrace();
//            System.out.println("User not found");
//            return ResponseEntity.notFound().build();
//        }
//
//        UserDetails userDetails = this.userSessionService.loadUserByUsername(jwtRequest.getUsername());
//        String token = this.jwtUtils.generateToken(userDetails);
//
//        return ResponseEntity.ok(new JwtResponse(token));
//    }

    private void authenticate(String username, String password){
        try{
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (DisabledException disabledException){
            System.out.println("usuario desabilitado");
        }catch (BadCredentialsException badCredentialsException){
            System.out.println("invalid credentials");
        }
    }

}

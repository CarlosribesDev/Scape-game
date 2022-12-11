package com.games.calendar.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings")
@CrossOrigin
public class GreetingController {

    @GetMapping
    public ResponseEntity<String> sayHello(){

        return  ResponseEntity.ok("pasa gente");
    }

    @GetMapping("/bye")
    public ResponseEntity<String> sayBye(){
        return  ResponseEntity.ok("por la sombra");
    }
}

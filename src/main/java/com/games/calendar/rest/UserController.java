package com.games.calendar.rest;

import com.games.calendar.model.User;
import com.games.calendar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable final Long id){
        return ResponseEntity.ok(this.userService.retrieveUserById(id));
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> saveUser(@RequestBody final User user){
        return ResponseEntity.ok(this.userService.saveUser(user));
    }
}

package com.games.calendar.rest;

import com.games.calendar.model.User;
import com.games.calendar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable final Long id){
        return ResponseEntity.ok(this.userService.retrieveUserById(id));
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(this.userService.retrieveUsers());
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> saveUser(@RequestBody final User user){
        return ResponseEntity.ok(this.userService.saveUser(user));
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable final Long id, @RequestBody final User user){
        return ResponseEntity.ok(this.userService.updateUser(id, user));
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable final Long id){
        this.userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }


}

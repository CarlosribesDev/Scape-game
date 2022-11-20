package com.games.calendar.rest;

import com.games.calendar.model.Day;
import com.games.calendar.service.DayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DayController {

    private final DayService dayService;

    @RequestMapping(value = "/day/{id}", method = RequestMethod.GET)
    public ResponseEntity<Day> getDay(@PathVariable final Long id){
        return ResponseEntity.ok(this.dayService.retrieveDayById(id));
    }

    @RequestMapping(value = "/day", method = RequestMethod.GET)
    public ResponseEntity<List<Day>> getDays(){
        return ResponseEntity.ok(this.dayService.retrieveDays());
    }

    @RequestMapping(value = "/day", method = RequestMethod.POST)
    public ResponseEntity<Day> saveDay(@RequestBody final Day day){
        return ResponseEntity.ok(this.dayService.saveDay(day));
    }

    @RequestMapping(value = "/day/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Day> updateDay(@PathVariable final Long id, @RequestBody final Day day){
        return ResponseEntity.ok(this.dayService.updateDay(id, day));
    }

    @RequestMapping(value = "/day/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteDay(@PathVariable final Long id){
        this.dayService.deleteDay(id);
        return ResponseEntity.ok().build();
    }
}

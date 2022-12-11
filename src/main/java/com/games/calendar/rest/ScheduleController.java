package com.games.calendar.rest;

import com.games.calendar.model.Schedule;
import com.games.calendar.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin (origins = "*" , exposedHeaders = "**")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @RequestMapping(value = "/schedule/{id}", method = RequestMethod.GET)
    public ResponseEntity<Schedule> getSchedule(@PathVariable final Long id){
        return ResponseEntity.ok(this.scheduleService.retrieveScheduleById(id));
    }

    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public ResponseEntity<List<Schedule>> getSchedules(){
        return ResponseEntity.ok(this.scheduleService.retrieveSchedules());
    }

    @RequestMapping(value = "/schedule", method = RequestMethod.POST)
    public ResponseEntity<Schedule> saveSchedule(@RequestBody final Schedule schedule){
        return ResponseEntity.ok(this.scheduleService.saveSchedule(schedule));
    }

    @RequestMapping(value = "/schedule/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Schedule> updateSchedule(@PathVariable final Long id, @RequestBody final Schedule schedule){
        return ResponseEntity.ok(this.scheduleService.updateSchedule(id, schedule));
    }

    @RequestMapping(value = "/schedule/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSchedule(@PathVariable final Long id){
        this.scheduleService.deleteSchedule(id);
        return ResponseEntity.ok().build();
    }


}

package com.games.calendar.rest;

import com.games.calendar.model.Booking;
import com.games.calendar.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class BookingController {

    private final BookingService bookingService;

    @RequestMapping(value = "/booking/{id}", method = RequestMethod.GET)
    public ResponseEntity<Booking> getBooking(@PathVariable final Long id){
        return ResponseEntity.ok(this.bookingService.retrieveBookingById(id));
    }

    @RequestMapping(value = "/booking", method = RequestMethod.GET)
    public ResponseEntity<List<Booking>> getBookings(){
        return ResponseEntity.ok(this.bookingService.retrieveBookings());
    }

    @RequestMapping(value = "/booking", method = RequestMethod.POST)
    public ResponseEntity<Booking> saveBooking(@RequestBody final Booking booking){
        return ResponseEntity.ok(this.bookingService.saveBooking(booking));
    }

    @RequestMapping(value = "/booking/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Booking> updateBooking(@PathVariable final Long id, @RequestBody final Booking booking){
        return ResponseEntity.ok(this.bookingService.updateBooking(id, booking));
    }

    @RequestMapping(value = "/booking/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteBooking(@PathVariable final Long id){
        this.bookingService.deleteBooking(id);
        return ResponseEntity.ok().build();
    }


}
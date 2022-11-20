package com.games.calendar.service;

import com.games.calendar.mapper.BookingMapper;
import com.games.calendar.model.Booking;
import com.games.calendar.persistence.entity.BookingEntity;
import com.games.calendar.persistence.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public Booking saveBooking(final Booking booking){
        BookingEntity bookingSaved = this.bookingRepository.save(this.bookingMapper.modelToEntity(booking));
        return this.bookingMapper.entityToModel(bookingSaved);
    }
    public Booking updateBooking(final Long id, final Booking booking){
        Assert.isTrue(this.bookingRepository.existsById(id),"booking dont exist");
        BookingEntity bookingUpdated = this.bookingRepository.save(this.bookingMapper.modelToEntity(booking));

        return this.bookingMapper.entityToModel(bookingUpdated);
    }

    public Booking retrieveBookingById(final Long id) {
        BookingEntity bookingEntity = this.bookingRepository.findById(id).orElseThrow();
        return this.bookingMapper.entityToModel(bookingEntity);
    }

    public List<Booking> retrieveBookings(){
        return this.bookingMapper.entitiesToModels(this.bookingRepository.findAll());
    }

    public void deleteBooking(final Long id){
        Assert.isTrue(this.bookingRepository.existsById(id),"booking dont exist");
        this.bookingRepository.deleteById(id);
    }
}

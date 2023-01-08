package com.games.calendar.mapper;

import com.games.calendar.model.Booking;
import com.games.calendar.persistence.entity.BookingEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingMapper implements EntityMapper<BookingEntity, Booking> {

    private final ModelMapper modelMapper;
    @Override
    public Booking entityToModel(BookingEntity bookingEntity) {

        Booking booking = modelMapper.map(bookingEntity,Booking.class);
        booking.setUserId(booking.getUserId());

        return booking;
    }

    @Override
    public BookingEntity modelToEntity(Booking booking) {

        return modelMapper.map(booking,BookingEntity.class);
    }
}

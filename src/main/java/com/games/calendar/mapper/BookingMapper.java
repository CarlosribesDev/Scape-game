package com.games.calendar.mapper;

import com.games.calendar.model.Booking;
import com.games.calendar.model.Game;
import com.games.calendar.model.UserWithoutBookings;
import com.games.calendar.persistence.entity.BookingEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingMapper implements EntityMapper<BookingEntity, Booking> {

    private final ModelMapper modelMapper;
    private final UserWithOutBookingMapper userWithOutBookingMapper;
    private final GameMapper gameMapper;

    @Override
    public Booking entityToModel(BookingEntity bookingEntity) {

        final Booking booking = this.modelMapper.map(bookingEntity, Booking.class);
        if(bookingEntity.getUser() != null){
            final UserWithoutBookings user = this.userWithOutBookingMapper.entityToModel(bookingEntity.getUser());
            booking.setUser(user);
        }

        if(booking.getGame() != null){
            final Game game = this.gameMapper.entityToModel(bookingEntity.getGame());
            booking.setGame(game);
        }

        return booking;
    }

    @Override
    public BookingEntity modelToEntity(Booking booking) {
        return modelMapper.map(booking, BookingEntity.class);
    }
}

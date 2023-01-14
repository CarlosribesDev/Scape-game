package com.games.calendar.service;

import com.games.calendar.mapper.BookingMapper;
import com.games.calendar.model.Booking;
import com.games.calendar.persistence.entity.BookingEntity;
import com.games.calendar.persistence.entity.DayEntity;
import com.games.calendar.persistence.entity.GameEntity;
import com.games.calendar.persistence.entity.UserEntity;
import com.games.calendar.persistence.repository.BookingRepository;
import com.games.calendar.persistence.repository.DayRepository;
import com.games.calendar.persistence.repository.GameRepository;
import com.games.calendar.persistence.repository.UserRepository;
import com.games.calendar.request.UserBookingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    private final EmailService emailService;

    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    private final DayRepository dayRepository;




    public Booking saveBooking(final Booking booking){
        BookingEntity bookingSaved = this.bookingRepository.save(this.bookingMapper.modelToEntity(booking));
        return this.bookingMapper.entityToModel(bookingSaved);
    }
    public Booking updateBooking(final Long id, final Booking booking){
        Assert.isTrue(this.bookingRepository.existsById(id),"booking dont exist");
        BookingEntity bookingUpdated = this.bookingRepository.save(this.bookingMapper.modelToEntity(booking));

        return this.bookingMapper.entityToModel(bookingUpdated);
    }

    public Booking updateBookingUser(final Long id, final UserBookingRequest userBookingRequest){
        Assert.isTrue(this.bookingRepository.existsById(id),"booking dont exist");
        Assert.isTrue(this.userRepository.existsById(userBookingRequest.getUserId()),"user dont exist");
        final UserEntity user = this.userRepository.findById(userBookingRequest.getUserId()).orElseThrow();
        final BookingEntity booking = this.bookingRepository.findById(id).orElseThrow();
        final GameEntity game = this.gameRepository.findById(userBookingRequest.getGameId()).orElseThrow();
        booking.setUser(user);
        booking.setGame(game);

        final String subject = "Su reserva ha sido aceptada";
        final String text = "Te es peramos el " + booking.getDate() + " a las " + booking.getHour()  + ". Nuestra dirección es C/Antonio Vera nº34 Elda";

 //       this.emailService.sendEmail(user.getEmail(), subject ,text);

        final DayEntity day = booking.getDay();

        if(day.getBookings().stream().noneMatch(bookingEntity -> bookingEntity.getUser() == null)){
            day.setIsBusy(true);
            this.dayRepository.save(day);
        }

        return this.bookingMapper.entityToModel(this.bookingRepository.save(booking));
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

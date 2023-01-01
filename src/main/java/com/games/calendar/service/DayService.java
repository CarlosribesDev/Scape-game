package com.games.calendar.service;

import com.games.calendar.mapper.BookingMapper;
import com.games.calendar.mapper.DayMapper;
import com.games.calendar.model.Day;
import com.games.calendar.persistence.entity.BookingEntity;
import com.games.calendar.persistence.entity.DayEntity;
import com.games.calendar.persistence.repository.BookingRepository;
import com.games.calendar.persistence.repository.DayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DayService {

    private final DayRepository dayRepository;

    private final BookingRepository bookingRepository;
    private final DayMapper dayMapper;
    private final BookingMapper bookingMapper;

    public Day saveDay(final Day day){
        DayEntity daySaved = this.dayRepository.save(this.dayMapper.modelToEntity(day));
        return this.dayMapper.entityToModel(daySaved);
    }
    public Day updateDay(final Long id, final Day day){
        Assert.isTrue(this.dayRepository.existsById(id), "day dont exist");
        DayEntity dayUpdated = this.dayRepository.save(this.dayMapper.modelToEntity(day));

        return this.dayMapper.entityToModel(dayUpdated);
    }

    public List<Day> updateDays(final List<Day> days){

        days.forEach(day -> {

            final DayEntity dayEntity = this.dayRepository.findById(day.getId()).orElseThrow();

            final Set<BookingEntity> oldBookings = dayEntity.getBookings();

            if (oldBookings != null && oldBookings.size() > 0){
                this.bookingRepository.deleteAll(oldBookings);
                dayEntity.setBookings(null);
                final Set<BookingEntity> newBookings = new HashSet<>();
                day.getBookings().forEach(booking -> {
                    final BookingEntity newBooking = this.bookingMapper.modelToEntity(booking);
                    newBooking.setUser(null);
                    newBooking.setDay(dayEntity);
                    newBooking.setDate(day.getDate());
                    newBookings.add(newBooking);
                });

                dayEntity.setBookings(newBookings);
                this.dayRepository.save(dayEntity);
                return;
            }

            day.getBookings().forEach(booking -> {
                final BookingEntity newBooking = this.bookingMapper.modelToEntity(booking);
                newBooking.setUser(null);
                newBooking.setDay(dayEntity);
                newBooking.setDate(day.getDate());
                this.bookingRepository.save(newBooking);
            });
        });

        LocalDate date = days.get(0).getDate();

        int year = date.getYear();
        int month = date.getMonthValue();

        return retrieveDaysInMonth(year, month);
    }

    public Day retrieveDayById(final Long id) {
        DayEntity dayEntity = this.dayRepository.findById(id).orElseThrow();
        return this.dayMapper.entityToModel(dayEntity);
    }

    public List<Day> retrieveDaysInMonth(final int year,int month){
        final LocalDate startDate = LocalDate.of(year, month, 1);
        final int daysInMonth = startDate.getMonth().length(startDate.isLeapYear());
        final LocalDate endDate = startDate.withDayOfMonth(daysInMonth);

        final Map<LocalDate,List<DayEntity>> daysByDate = this.dayRepository.getDaysBetweenDates(startDate,endDate)
                .stream().collect(Collectors.groupingBy(DayEntity::getDate));

        final List<DayEntity> daysToRetrieve = new ArrayList<>();

        for (int dayNumber = 1; dayNumber  <= daysInMonth; dayNumber++) {
            final LocalDate dayDate = LocalDate.of(year, month, dayNumber);

            if(daysByDate.containsKey(dayDate)){
                daysToRetrieve.add(daysByDate.get(dayDate).get(0));
            }else{
                Day newDay = new Day();
                newDay.setDate(dayDate);

                DayEntity daySaved = this.dayRepository.save(this.dayMapper.modelToEntity(newDay));

                daysToRetrieve.add(daySaved);
            }
        }

        return this.dayMapper.entitiesToModels(daysToRetrieve);
    }

    public List<Day> retrieveDays(){
        return this.dayMapper.entitiesToModels(this.dayRepository.findAll());
    }

    public void deleteDay(final Long id){
        Assert.isTrue(this.dayRepository.existsById(id),"day dont exist");
        this.dayRepository.deleteById(id);
    }
}

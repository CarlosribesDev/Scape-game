package com.games.calendar.persistence.repository;

import com.games.calendar.persistence.entity.BookingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<BookingEntity,Long> {
}

package com.games.calendar.persistence.repository;

import com.games.calendar.persistence.entity.HourEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HourRepository extends CrudRepository<HourEntity,Long> {
}

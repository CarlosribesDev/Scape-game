package com.games.calendar.persistence.repository;

import com.games.calendar.persistence.entity.DayEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayRepository extends CrudRepository<DayEntity,Long> {
}

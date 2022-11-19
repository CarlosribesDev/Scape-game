package com.games.calendar.persistence.repository;

import com.games.calendar.persistence.entity.ScheduleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends CrudRepository<ScheduleEntity,Long> {
}

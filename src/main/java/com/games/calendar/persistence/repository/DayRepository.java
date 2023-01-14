package com.games.calendar.persistence.repository;

import com.games.calendar.persistence.entity.DayEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DayRepository extends CrudRepository<DayEntity,Long> {

    @Query(value =
            """
            select * FROM day
            where day.day_date >= :startDate and day.day_date <= :endDate
            """,nativeQuery = true)
    List<DayEntity> getDaysBetweenDates(@Param("startDate")final LocalDate startDate,
                                        @Param("endDate")final LocalDate endDate);
}

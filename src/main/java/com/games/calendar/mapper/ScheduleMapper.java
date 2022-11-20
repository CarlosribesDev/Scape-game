package com.games.calendar.mapper;

import com.games.calendar.model.Schedule;
import com.games.calendar.persistence.entity.ScheduleEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleMapper implements EntityMapper<ScheduleEntity, Schedule> {

    private final ModelMapper modelMapper;
    @Override
    public Schedule entityToModel(final ScheduleEntity scheduleEntity) {
        return modelMapper.map(scheduleEntity,Schedule.class);
    }

    @Override
    public ScheduleEntity modelToEntity(final Schedule schedule) {
        return modelMapper.map(schedule,ScheduleEntity.class);
    }
}

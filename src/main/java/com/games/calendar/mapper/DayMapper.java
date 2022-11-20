package com.games.calendar.mapper;

import com.games.calendar.model.Day;
import com.games.calendar.persistence.entity.DayEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DayMapper implements EntityMapper<DayEntity, Day> {

    private final ModelMapper modelMapper;
    @Override
    public Day entityToModel(final DayEntity dayEntity) {
        return modelMapper.map(dayEntity,Day.class);
    }

    @Override
    public DayEntity modelToEntity(final Day day) {
        return modelMapper.map(day,DayEntity.class);
    }
}

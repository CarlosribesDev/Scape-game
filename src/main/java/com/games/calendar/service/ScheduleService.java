package com.games.calendar.service;

import com.games.calendar.mapper.ScheduleMapper;
import com.games.calendar.model.Schedule;
import com.games.calendar.persistence.entity.ScheduleEntity;
import com.games.calendar.persistence.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    public Schedule saveSchedule(final Schedule schedule){
        ScheduleEntity scheduleSaved = this.scheduleRepository.save(this.scheduleMapper.modelToEntity(schedule));
        return this.scheduleMapper.entityToModel(scheduleSaved);
    }

    public Schedule updateSchedule(final Long id,final Schedule schedule){
        Assert.isTrue(this.scheduleRepository.existsById(id), "Schedule dont exist");
        ScheduleEntity scheduleUpdated = this.scheduleRepository.save(this.scheduleMapper.modelToEntity(schedule));

        return this.scheduleMapper.entityToModel(scheduleUpdated);
    }

    public Schedule retrieveScheduleById(final Long id) {
        ScheduleEntity schedule = scheduleRepository.findById(id).orElseThrow();
        return scheduleMapper.entityToModel(schedule);
    }

    public List<Schedule> retrieveSchedules(){
        return scheduleMapper.entitiesToModels(scheduleRepository.findAll());
    }

    public void deleteSchedule(final Long id){
        Assert.isTrue(this.scheduleRepository.existsById(id),"Schedule dont exist");
        this.scheduleRepository.deleteById(id);
    }
}

package com.games.calendar.service;

import com.games.calendar.mapper.DayMapper;
import com.games.calendar.model.Day;
import com.games.calendar.persistence.entity.DayEntity;
import com.games.calendar.persistence.repository.DayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DayService {

    private final DayRepository dayRepository;
    private final DayMapper dayMapper;

    public Day saveDay(final Day day){
        DayEntity daySaved = this.dayRepository.save(this.dayMapper.modelToEntity(day));
        return this.dayMapper.entityToModel(daySaved);
    }
    public Day updateDay(final Long id, final Day day){
        Assert.isTrue(this.dayRepository.existsById(id), "day dont exist");
        DayEntity dayUpdated = this.dayRepository.save(this.dayMapper.modelToEntity(day));

        return this.dayMapper.entityToModel(dayUpdated);
    }

    public Day retrieveDayById(final Long id) {
        DayEntity dayEntity = this.dayRepository.findById(id).orElseThrow();
        return this.dayMapper.entityToModel(dayEntity);
    }

    public List<Day> retrieveDays(){
        return this.dayMapper.entitiesToModels(this.dayRepository.findAll());
    }

    public void deleteDay(final Long id){
        Assert.isTrue(this.dayRepository.existsById(id),"day dont exist");
        this.dayRepository.deleteById(id);
    }
}

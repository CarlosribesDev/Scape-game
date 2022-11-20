package com.games.calendar.mapper;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public interface EntityMapper <E, M> {

    M entityToModel(E e);


    default List<M> entitiesToModels(List<E> e){
        if(e == null) return null;

        return e.stream().map(this::entityToModel).collect(Collectors.toList());
    }

    E modelToEntity(M m);

    default List<E> modelsToEntities(List<M> m){
        if(m == null) return null;

        return m.stream().map(this::modelToEntity).collect(Collectors.toList());
    }
}

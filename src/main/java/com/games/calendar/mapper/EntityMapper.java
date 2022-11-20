package com.games.calendar.mapper;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface EntityMapper <E, M> {

    M entityToModel(final E e);

    default List<M> entitiesToModels(final List<E> e){
        if(e == null) return null;

        return e.stream().map(this::entityToModel).collect(Collectors.toList());
    }

    default List<M> entitiesToModels(final Iterable<E> e){
        if(e == null) return null;

        return StreamSupport.stream(e.spliterator(), false).map(this::entityToModel).collect(Collectors.toList());
    }

    E modelToEntity(final M m);

    default List<E> modelsToEntities(final List<M> m){
        if(m == null) return null;

        return m.stream().map(this::modelToEntity).collect(Collectors.toList());
    }

    default List<E> modelsToEntities(final Iterable<M> m){
        if(m == null) return null;

        return StreamSupport.stream(m.spliterator(), false).map(this::modelToEntity).collect(Collectors.toList());
    }
}

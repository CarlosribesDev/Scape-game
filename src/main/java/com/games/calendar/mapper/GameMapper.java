package com.games.calendar.mapper;

import com.games.calendar.model.Game;
import com.games.calendar.persistence.entity.GameEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameMapper implements EntityMapper<GameEntity, Game> {

    private final ModelMapper modelMapper;
    @Override
    public Game entityToModel(final GameEntity gameEntity) {
        return modelMapper.map(gameEntity,Game.class);
    }

    @Override
    public GameEntity modelToEntity(final Game game) {
        return modelMapper.map(game,GameEntity.class);
    }
}

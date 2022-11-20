package com.games.calendar.service;

import com.games.calendar.mapper.GameMapper;
import com.games.calendar.model.Game;
import com.games.calendar.persistence.entity.GameEntity;
import com.games.calendar.persistence.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    public Game saveGame(final Game game){
        GameEntity gameSaved = this.gameRepository.save(this.gameMapper.modelToEntity(game));
        return this.gameMapper.entityToModel(gameSaved);
    }
    public Game updateGame(final Long id, final Game game){
        Assert.isTrue(this.gameRepository.existsById(id),"Game dont exist");
        GameEntity GameUpdated = this.gameRepository.save(this.gameMapper.modelToEntity(game));

        return this.gameMapper.entityToModel(GameUpdated);
    }

    public Game retrieveGameById(final Long id) {
        GameEntity GameEntity = this.gameRepository.findById(id).orElseThrow();
        return this.gameMapper.entityToModel(GameEntity);
    }

    public List<Game> retrieveGames(){
        return this.gameMapper.entitiesToModels(this.gameRepository.findAll());
    }

    public void deleteGame(final Long id){
        Assert.isTrue(this.gameRepository.existsById(id),"Game dont exist");
        this.gameRepository.deleteById(id);
    }

}

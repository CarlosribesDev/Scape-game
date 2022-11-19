package com.games.calendar.persistence.repository;

import com.games.calendar.persistence.entity.GameEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<GameEntity,Long> {
}

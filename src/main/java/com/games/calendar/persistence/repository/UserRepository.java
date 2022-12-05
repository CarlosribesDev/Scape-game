package com.games.calendar.persistence.repository;

import com.games.calendar.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long>{

    Optional<UserEntity> findByEmail(final String email);
    Optional<UserEntity> findByTelephone(final String telephone);
    boolean existsByEmail(final String email);
}

package com.games.calendar.persistence.repository;

import com.games.calendar.persistence.entity.UserAuth;
import com.games.calendar.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserAuthRepository extends CrudRepository<UserAuth,Long> {

    Optional<UserAuth> findByUsername(final String username);
}

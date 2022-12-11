package com.games.calendar.persistence.repository;

import com.games.calendar.persistence.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserAuthRepository extends CrudRepository<UserAuthEntity,Long> {

    Optional<UserAuthEntity> findByUsername(final String username);

    @Query(value =
    """
    select au.username FROM user_auth au
    where au.user_id = :userId
    """,nativeQuery = true)
    List<String> findUsernameByUserId(@Param("userId")final Long userId);

    @Query(value =
            """
            select au.role FROM user_auth au
            where au.user_id = :userId
            """,nativeQuery = true)
    List<String> findRoleByUserId(@Param("userId")final Long userId);
    boolean existsByUsername( final String username);
}

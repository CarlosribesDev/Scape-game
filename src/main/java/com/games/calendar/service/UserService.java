package com.games.calendar.service;

import com.games.calendar.mapper.UserMapper;
import com.games.calendar.model.User;
import com.games.calendar.model.constants.RoleType;
import com.games.calendar.persistence.entity.UserEntity;
import com.games.calendar.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User saveUser(final User user){

        user.setRole(RoleType.USER);
        UserEntity userSaved = userRepository.save(userMapper.modelToEntity(user));
        return userMapper.entityToModel(userSaved);
    }

    public User retrieveUserById(final Long id){

        UserEntity userEntity = userRepository.findById(id).orElseThrow();
        return userMapper.entityToModel(userEntity);
    }
}

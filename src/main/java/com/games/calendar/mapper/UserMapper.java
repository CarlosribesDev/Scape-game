package com.games.calendar.mapper;


import com.games.calendar.model.User;
import com.games.calendar.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper implements EntityMapper<UserEntity, User> {

    private final ModelMapper modelMapper;
    @Override
    public User entityToModel(final UserEntity userEntity) {
        return modelMapper.map(userEntity,User.class);
    }

    @Override
    public UserEntity modelToEntity(final User user) {
        return modelMapper.map(user,UserEntity.class);
    }
}

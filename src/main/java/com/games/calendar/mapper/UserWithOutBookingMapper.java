package com.games.calendar.mapper;

import com.games.calendar.model.UserWithoutBookings;
import com.games.calendar.persistence.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserWithOutBookingMapper implements EntityMapper<UserEntity, UserWithoutBookings>  {

    private final ModelMapper modelMapper;

    @Override
    public UserWithoutBookings entityToModel(UserEntity userEntity) {
        return this.modelMapper.map(userEntity, UserWithoutBookings.class);
    }

    @Override
    public UserEntity modelToEntity(UserWithoutBookings user) {
        return null;
    }
}

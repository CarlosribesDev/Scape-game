package com.games.calendar.mapper;

import com.games.calendar.model.Booking;
import com.games.calendar.model.User;
import com.games.calendar.persistence.entity.UserEntity;
import com.games.calendar.persistence.repository.UserAuthRepository;
import com.games.calendar.request.NewUserRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper implements EntityMapper<UserEntity, User> {

    private final ModelMapper modelMapper;
    private final BookingMapper bookingMapper;

    @Override
    public User entityToModel(final UserEntity userEntity) {

        List<Booking> bookings = bookingMapper.entitiesToModels(userEntity.getBookings());
        User user = new User();
        user.setId(userEntity.getId());
        user.setName(userEntity.getName());
        user.setSurname(userEntity.getSurname());
        user.setEmail(userEntity.getEmail());
        user.setTelephone(userEntity.getTelephone());
        user.setBookings(new HashSet<>(bookings));

        return user;
    }

    @Override
    public UserEntity modelToEntity(final User user) {
        return modelMapper.map(user,UserEntity.class);
    }

    public UserEntity newUserRequestToUserEntity(NewUserRequest userRequest){
        UserEntity user = new UserEntity();
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setEmail(userRequest.getEmail());
        user.setTelephone(userRequest.getTelephone());

        return user;
    }

}

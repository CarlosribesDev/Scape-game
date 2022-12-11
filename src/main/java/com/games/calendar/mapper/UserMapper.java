package com.games.calendar.mapper;


import com.games.calendar.model.User;
import com.games.calendar.model.constants.RoleType;
import com.games.calendar.persistence.entity.UserEntity;
import com.games.calendar.persistence.repository.UserAuthRepository;
import com.games.calendar.request.NewUserRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper implements EntityMapper<UserEntity, User> {

    private final ModelMapper modelMapper;
    private final UserAuthRepository userAuthRepository;
    @Override
    public User entityToModel(final UserEntity userEntity) {

        User user = modelMapper.map(userEntity,User.class);
        user.setUsername(userAuthRepository.findUsernameByUserId(user.getId()).get(0));
        user.setRole(RoleType.valueOf(userAuthRepository.findRoleByUserId(user.getId()).get(0)));

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

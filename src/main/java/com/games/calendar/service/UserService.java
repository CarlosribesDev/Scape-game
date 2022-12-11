package com.games.calendar.service;

import com.games.calendar.mapper.UserMapper;
import com.games.calendar.model.User;
import com.games.calendar.model.constants.RoleType;
import com.games.calendar.persistence.entity.UserAuthEntity;
import com.games.calendar.persistence.entity.UserEntity;
import com.games.calendar.persistence.repository.UserAuthRepository;
import com.games.calendar.persistence.repository.UserRepository;
import com.games.calendar.request.NewUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserAuthRepository userAuthRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public User saveUser(final NewUserRequest userRequest){
        Assert.isTrue(!this.userRepository.existsByTelephone(userRequest.getTelephone()), "Ya existe un usuario con ese teléfono");
        Assert.isTrue(!this.userRepository.existsByEmail(userRequest.getEmail()), "Ya existe un usuario con ese email");
        Assert.isTrue(!this.userAuthRepository.existsByUsername(userRequest.getUsername()), "Nombre de usuario ya existe");

        UserEntity userSaved = this.userRepository.save(userMapper.newUserRequestToUserEntity(userRequest));

        UserAuthEntity newAuth = new UserAuthEntity();
        newAuth.setUser(userSaved);
        newAuth.setRole(RoleType.ROLE_USER);
        newAuth.setUsername(userRequest.getUsername());
        newAuth.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        this.userAuthRepository.save(newAuth);

        return this.userMapper.entityToModel(userSaved);
    }
    public User updateUser(final Long id,final User user){
        Assert.isTrue(this.userRepository.existsById(id), "User dont exist");
        UserEntity userUpdated = this.userRepository.save(this.userMapper.modelToEntity(user));

        return this.userMapper.entityToModel(userUpdated);
    }

    public User retrieveUserById(final Long id) {
        UserEntity userEntity = this.userRepository.findById(id).orElseThrow();
        return this.userMapper.entityToModel(userEntity);
    }

    public List<User> retrieveUsers(){
        return this.userMapper.entitiesToModels(this.userRepository.findAll());
    }

    public void deleteUser(final Long id){
        Assert.isTrue(this.userRepository.existsById(id),"User dont exist");
        this.userRepository.deleteById(id);
    }

    public boolean emailExist(String email){
        return this.userRepository.existsByEmail(email);
    }

    public boolean usernameExist(String username){
        return this.userAuthRepository.existsByUsername(username);
    }

    public boolean telephoneExist(String telephone){
        return this.userRepository.existsByTelephone(telephone);
    }
}

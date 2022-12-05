package com.games.calendar.service;

import com.games.calendar.mapper.UserMapper;
import com.games.calendar.model.User;
import com.games.calendar.model.constants.RoleType;
import com.games.calendar.persistence.entity.UserEntity;
import com.games.calendar.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User saveUser(final User user){
        Assert.isTrue(this.userRepository.findByTelephone(user.getTelephone()).isEmpty(), "Ya existe un usuario con ese teléfono");
        Assert.isTrue(userRepository.findByEmail(user.getEmail()).isEmpty(), "Ya existe un usuario con ese email");

        user.setRole(RoleType.USER);
        UserEntity userSaved = this.userRepository.save(this.userMapper.modelToEntity(user));

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
}

package com.livethegame.ManageUser.services;

import com.livethegame.ManageUser.common.UserRequestMapper;
import com.livethegame.ManageUser.common.UserResponseMapper;
import com.livethegame.ManageUser.dto.UserRequest;
import com.livethegame.ManageUser.entities.Users;
import com.livethegame.ManageUser.exception.UserNotFoundException;
import com.livethegame.ManageUser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserResponseMapper userResponseMapper;
    @Autowired
    UserRequestMapper userRequestMapper;

    public void updateUserDetails(UserRequest input){
        Optional<Users> OptionalUser = userRepository.findById(input.getId());
        if(OptionalUser.isPresent()){
            Users users = OptionalUser.get();
            users.setBirthdate(input.getBirthdate());
            users.setCountry(input.getCountry());
            users.setCurrency(input.getCurrency());
            users.setIdentification_number(input.getIdentification_number());
            users.setIdentification_type(input.getIdentification_type());
            users.setName(input.getName());
            users.setLastname(input.getLastname());
            users.setPhone(input.getPhone());
            users.setLast_updated();
            userRepository.save(users);
        }else {
            throw new UserNotFoundException("Usuario no encontrado con ID: " + input.getId());
        }

    }
    public void confirmEmail(Long id){
        Optional<Users> OptionalUser = userRepository.findById(id);
        if(OptionalUser.isPresent()){
            Users users = OptionalUser.get();
            users.setIs_active();
            users.setIs_confirmed();
            users.setLast_updated();
            userRepository.save(users);
        } else {
            throw new UserNotFoundException("Usuario no encontrado con ID: " + id);
        }
    }

}

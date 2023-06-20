package com.dibb.quizvision.services;

import com.dibb.quizvision.dtos.UserDto;
import com.dibb.quizvision.entities.User;
import com.dibb.quizvision.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<String> addUser(UserDto userDto){
        List<String> response = new ArrayList<>();
        User user = new User(userDto);
        userRepository.saveAndFlush(user);
        response.add("User has been added successfully.");
        return response;
    }

    @Override
    public List<String> loginUser(UserDto userDto){
        List<String> response = new ArrayList<>();
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());
        if (userOptional.isPresent()){
            if (userDto.getPassword().equals(userOptional.get().getPassword())){
                response.add("User logged in successfully");
                response.add(String.valueOf(userOptional.get().getId()));
            } else {
                response.add("Username of Password are incorrect");
            }
        } else {
            response.add("Username or Password are incorrect");
        }
        return response;
    }
}

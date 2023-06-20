package com.dibb.quizvision.controllers;

import com.dibb.quizvision.dtos.UserDto;
import com.dibb.quizvision.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public List<String> addUser(@RequestBody UserDto userDto){
        userDto.setPassword(userDto.getPassword());
        return userService.addUser(userDto);
    }

    @PostMapping("/login")
    public List<String> loginUser(@RequestBody UserDto userDto){
        return userService.loginUser(userDto);
    }
}

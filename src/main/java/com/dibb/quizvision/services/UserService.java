package com.dibb.quizvision.services;

import com.dibb.quizvision.dtos.UserDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    @Transactional
    List<String> addUser(UserDto userDto);

    List<String> loginUser(UserDto userDto);
}

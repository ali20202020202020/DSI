package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();
    User getUserById(Long id);



    User updateStudent(User user);
    void deleteStudentById(Long id);
}

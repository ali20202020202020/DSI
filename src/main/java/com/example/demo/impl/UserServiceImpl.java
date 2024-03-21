package com.example.demo.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        super();
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto studentDto) {
        User student = new User();

        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());

        // Encrypt the password using Spring Security
        student.setPassword(passwordEncoder.encode(studentDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        student.setRoles(Arrays.asList(role));
        userRepository.save(student);
    }
    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    @Override
    public User findUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }
    @Override
    public List<UserDto> findAllUsers() {
        List<User>students = userRepository.findAll();

        return students.stream()
                .map((student) -> mapToStudentDto(student))
                .collect(Collectors.toList());
    }

    @Override
    public User getUserById(Long id) {
       return userRepository.findById(id).get();
    }




    @Override
    public User updateStudent(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteStudentById(Long id) {
        userRepository.deleteById(id);
    }

    private UserDto mapToStudentDto(User student) {
        UserDto studentDto = new UserDto();
        studentDto.setId(student.getId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setEmail(student.getEmail());
        return studentDto;
    }
}

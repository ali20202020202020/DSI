package com.example.demo.controller;

import java.util.List;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
public class UserAuthController {
    private UserService userService;

    @Autowired
    public UserAuthController(UserService userService)    {
        this.userService = userService;
    }
    // handler method to handle the home (index.html is home) page request
    @GetMapping("/index")
    public String home() {
        return "index";
    }

    // handler method handles the login request
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // handler method to handle the student registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {

        // create a model object to store form data

        UserDto user = new UserDto();
        model.addAttribute("user", user);

        return "register";
    }

    // handler method to handle student registration from submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result,
                               Model model) {
        User existingStudent = userService.findUserByEmail(userDto.getEmail());

        if (existingStudent != null && existingStudent.getEmail() != null && !existingStudent.getEmail().isEmpty()) {
            result.rejectValue("email", null, "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);

            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";

    }

    // handler method is used to handle a list of students
    @GetMapping("/users")
    public String students(Model model) {
        List<UserDto> users = userService.findAllUsers();

        model.addAttribute("users", users
        );

        return "users";

    }

    @RequestMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "edit_user";
    }

    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable Long id,
                             @ModelAttribute("user") @Valid UserDto userDto,
                             BindingResult result,
                             Model model){
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "edit_user";
        }

        User existingUser = userService.getUserById(id);
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        userService.updateStudent(existingUser);
        return "redirect:/users";
    }

    //Handler for Deletion
    @GetMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteStudentById(id);
        return "redirect:/users";
    }
}


package com.washzone.carwash.userservice.controller;

import com.washzone.carwash.userservice.dto.UserDto;
import com.washzone.carwash.userservice.model.UserModel;
import com.washzone.carwash.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/health")
    public String health() {
        return "Fine";
    }

    @PostMapping("/signup")
    public UserModel signup(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto,"Email");
    }

}

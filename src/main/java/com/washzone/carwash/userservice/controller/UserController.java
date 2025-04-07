package com.washzone.carwash.userservice.controller;

import com.washzone.carwash.userservice.Util.JwtUtil;
import com.washzone.carwash.userservice.dto.LoginDto;
import com.washzone.carwash.userservice.dto.UserDto;
import com.washzone.carwash.userservice.dto.UserProfile;
import com.washzone.carwash.userservice.model.User;
import com.washzone.carwash.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;


    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/health")
    public String health() {
        return "Fine";
    }

    @PostMapping("/signup")
    public LoginDto signup(@RequestBody UserDto userDto) {
        User userModel= userService.saveUser(userDto,"Email");
        String token=jwtUtil.generateToken(userModel.getEmail(),userModel.getUserRole().name());
        UserDto userDTo=new UserDto(userModel.getName(),userModel.getEmail(), userModel.getPassword(), userModel.getUserRole());
        return new LoginDto(token,userDTo);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto> userLogin(@RequestBody UserDto userDTO) {
        return userService.login(userDTO.getEmail(), userDTO.getPassword());
    }

    @GetMapping("/profile")
    public UserProfile getUserProfile(Authentication authentication) {
        String email = authentication.getName();

        return userService.getUserProfile(email);
    }

    @PutMapping("/profile")
    public UserProfile updateUserProfile(Authentication authentication,@RequestBody UserDto userDto) {
        String email = authentication.getName();
        return userService.updateUserProfile(email,userDto);
    }




}

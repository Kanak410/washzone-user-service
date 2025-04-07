package com.washzone.carwash.userservice.service;

import com.washzone.carwash.userservice.dto.CustomerDto;
import com.washzone.carwash.userservice.dto.UserDto;
import com.washzone.carwash.userservice.dto.WasherDto;
import com.washzone.carwash.userservice.model.UserModel;
import com.washzone.carwash.userservice.model.UserRole;
import com.washzone.carwash.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WasherService washerService;

    @Autowired
    private CustomerService customerService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public UserModel saveUser(UserDto userDto,String auth) {
        //if the email already exist it will return that old user details
        UserModel exitingUser = userRepository.findByEmail(userDto.getEmail()).orElse(null);
        if (exitingUser != null) {
            return exitingUser;
        }
        UserModel userModel = new UserModel(userDto.getName(), userDto.getEmail(), userDto.getPassword(), userDto.getUserRole());
        //excrypting the password
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userRepository.save(userModel);
        if(userModel.getUserRole() == UserRole.CUSTOMER) {
            customerService.save(userModel,new CustomerDto(userDto.getName(), userDto.getEmail()));

        }
        if(userModel.getUserRole() == UserRole.WASHER) {
            washerService.save(userModel,new WasherDto(userDto.getName(), userDto.getEmail(), true));
        }
        return userModel;
    }
}

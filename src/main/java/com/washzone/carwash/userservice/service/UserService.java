package com.washzone.carwash.userservice.service;

import com.washzone.carwash.userservice.Util.JwtUtil;
import com.washzone.carwash.userservice.dto.*;
import com.washzone.carwash.userservice.exceptions.UserNotFoundException;
import com.washzone.carwash.userservice.model.User;
import com.washzone.carwash.userservice.model.UserRole;
import com.washzone.carwash.userservice.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private WasherService washerService;

    @Autowired
    private JwtUtil jwtUtil;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);


    public User saveUser(UserDto userDTO, String auth){
        User exitingUser = userRepository.findByEmail(userDTO.getEmail()).orElse(null);

        if(exitingUser != null){
            return exitingUser;
        }
        User user = new User(userDTO.getName(),userDTO.getEmail(),userDTO.getPassword(),userDTO.getUserRole(),
                auth);
        if(auth.equals("Email")){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }



        // Crete customer or wahswer based on role
        userRepository.save(user);
        if(user.getUserRole() == (UserRole.CUSTOMER)){
            CustomerDto customerDto  = new CustomerDto(userDTO);
            customerService.save(user,customerDto);
        }
        if(user.getUserRole() == UserRole.WASHER){

            WasherDto washerDto = new WasherDto(userDTO);
            washerService.save(user,washerDto);

        }

        return user;
    }
    public ResponseEntity<LoginDto> login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email")); // Generic error to prevent email enumeration

        if (!user.getAuth().equals("Email")) {
            throw new UserNotFoundException("Invalid Email ");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getUserRole().name());
        UserDto userDto = new UserDto(user.getName(), user.getEmail(),null, user.getUserRole());

        return ResponseEntity.ok(new LoginDto(token, userDto));
    }

    public UserProfile getUserProfile(String email){
        User user =  userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found"));
        return new UserProfile(user.getName(), user.getEmail(), user.getUserRole()
                );
    }
    public UserProfile updateUserProfile(String email,UserDto userDto){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDto.getName());
        if(userDto.getPassword() != null && !userDto.getPassword().isEmpty() && "Email".equals(user.getAuth())){
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        User temp =  userRepository.save(user);
        return new UserProfile(temp.getName(), temp.getEmail(), temp.getUserRole()
                );
    }

    //helper method
    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }
}

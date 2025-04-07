package com.washzone.carwash.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String name;
    private String email;

    public CustomerDto(UserDto userDto) {
        this.name = userDto.getName();
        this.email = userDto.getEmail();
    }

}

package com.washzone.carwash.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WasherDto {
    private String name;
    private String email;
    private boolean isAvailable;
    public WasherDto(UserDto userDto) {
        this.name = userDto.getName();
        this.email = userDto.getEmail();
        this.isAvailable = true;
    }
}

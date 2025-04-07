package com.washzone.carwash.userservice.dto;

import com.washzone.carwash.userservice.model.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    public String name;
    public String email;
    @Enumerated(EnumType.STRING)
    private UserRole role;


}

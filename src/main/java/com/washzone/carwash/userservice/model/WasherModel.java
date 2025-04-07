package com.washzone.carwash.userservice.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "washer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WasherModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "user_id",nullable = false,unique = true)
    @JsonBackReference
    private UserModel user;
    private String name;
    private String email;
    private boolean isAvailable;


    public WasherModel( String name, String email, boolean isAvailable) {
        this.name = name;
        this.email = email;
        this.isAvailable = isAvailable;
    }
}

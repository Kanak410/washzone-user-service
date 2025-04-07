package com.washzone.carwash.userservice.service;

import com.washzone.carwash.userservice.dto.WasherDto;
import com.washzone.carwash.userservice.model.CustomerModel;
import com.washzone.carwash.userservice.model.User;
import com.washzone.carwash.userservice.model.WasherModel;
import com.washzone.carwash.userservice.repository.WasherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WasherService {
    @Autowired
    private WasherRepository washerRepository;

    public void save(User user, WasherDto washerDto) {
        WasherModel washerModel = new WasherModel(washerDto.getName(), washerDto.getEmail(),washerDto.isAvailable());
        washerModel.setUser(user);
        washerRepository.save(washerModel);
    }
}

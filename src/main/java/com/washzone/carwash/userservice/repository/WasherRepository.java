package com.washzone.carwash.userservice.repository;

import com.washzone.carwash.userservice.model.WasherModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WasherRepository extends JpaRepository<WasherModel, Long> {
}

package com.skilloo.api.repositories;

import com.skilloo.api.entities.Lab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabRepository extends JpaRepository<Lab, Long> {
}

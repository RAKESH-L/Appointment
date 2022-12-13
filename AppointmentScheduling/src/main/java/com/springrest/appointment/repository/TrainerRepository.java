package com.springrest.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.appointment.model.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Long>{

}

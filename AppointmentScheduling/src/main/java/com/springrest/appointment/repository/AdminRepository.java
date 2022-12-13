package com.springrest.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.appointment.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{

}

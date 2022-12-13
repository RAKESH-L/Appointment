package com.springrest.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.appointment.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);

}

package com.springrest.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springrest.appointment.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	@Query("select s from Student s where s.user.username=?1")
	Student getStudentByUsername(String username);

}

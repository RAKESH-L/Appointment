package com.springrest.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.appointment.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}

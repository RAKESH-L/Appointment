package com.springrest.appointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springrest.appointment.enums.StudentAppointmentStatus;
import com.springrest.appointment.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

	@Query("select a from Appointment a where a.student.user.username=?1")
	List<Appointment> getAllAppointment(String username);

	@Query("select s from Appointment s where s.status=?1")
	List<Appointment> getAllAppointmentByStatus(StudentAppointmentStatus statusEnum);

}

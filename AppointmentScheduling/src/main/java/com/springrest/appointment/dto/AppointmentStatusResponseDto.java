package com.springrest.appointment.dto;

import java.time.LocalDate;

public class AppointmentStatusResponseDto {

	private Long id;
	private String studentName;
	private String courseName;
	private Long availableSeats;
	private String trainerName;
	private LocalDate startDate;
	private String time;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getTrainerName() {
		return trainerName;
	}
	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public Long getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(Long availableSeats) {
		this.availableSeats = availableSeats;
	}
	@Override
	public String toString() {
		return "AppointmentStatusResponseDto [id=" + id + ", studentName=" + studentName + ", courseName=" + courseName
				+ ", availableSeats=" + availableSeats + ", trainerName=" + trainerName + ", startDate=" + startDate
				+ ", time=" + time + "]";
	}
	
	
	
}

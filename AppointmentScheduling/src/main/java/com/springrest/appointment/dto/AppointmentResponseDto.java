package com.springrest.appointment.dto;

import java.time.LocalDate;

import com.springrest.appointment.enums.StudentAppointmentStatus;

public class AppointmentResponseDto {

	private Long id;
	private String courseName;
	private double price;
	private String trainerName;
	private LocalDate startDate;
	private LocalDate endDate;
	private String time;
	private LocalDate appointmentDate;
	private StudentAppointmentStatus status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
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
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public StudentAppointmentStatus getStatus() {
		return status;
	}
	public void setStatus(StudentAppointmentStatus status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "AppointmentResponseDto [id=" + id + ", courseName=" + courseName + ", price=" + price + ", trainerName="
				+ trainerName + ", startDate=" + startDate + ", endDate=" + endDate + ", time=" + time
				+ ", appointmentDate=" + appointmentDate + ", status=" + status + "]";
	}
	
	
}

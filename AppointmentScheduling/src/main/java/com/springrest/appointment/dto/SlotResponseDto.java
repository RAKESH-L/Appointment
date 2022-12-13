package com.springrest.appointment.dto;

import java.time.LocalDate;

public class SlotResponseDto {

	private Long id;
	private LocalDate startDate;
	private LocalDate endDate;
	private String time;
	private Long availableSeats;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	public Long getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(Long availableSeats) {
		this.availableSeats = availableSeats;
	}
	@Override
	public String toString() {
		return "SlotResponseDto [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", time=" + time
				+ ", availableSeats=" + availableSeats + "]";
	}
	
}

package com.springrest.appointment.dto;

public class CourseResponseDto {

	private Long id;
	private String courseName;
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
	@Override
	public String toString() {
		return "CourseResponseDto [id=" + id + ", courseName=" + courseName + "]";
	}
	
	
}

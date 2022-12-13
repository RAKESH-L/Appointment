package com.springrest.appointment.dto;

public class CourseTrainerDto {

	private Long id;
	private String courseName;
	private double price;
	private String name;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "CourseTrainerDto [id=" + id + ", courseName=" + courseName + ", price=" + price + ", name=" + name
				+ "]";
	}
	
	
}

package com.springrest.appointment.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.appointment.dto.CourseResponseDto;
import com.springrest.appointment.dto.CourseTrainerDto;
import com.springrest.appointment.model.Course;
import com.springrest.appointment.model.Trainer;
import com.springrest.appointment.repository.CourseRepository;
import com.springrest.appointment.repository.TrainerRepository;

@RestController
@RequestMapping("/api/course")
@CrossOrigin(origins = {"http://localhost:8731"})
public class CourseController {

	@Autowired
	private CourseRepository courseRepository; // Injecting course Resource
	
	@Autowired 
	private TrainerRepository trainerRepository; // Injecting trainer Resource
	
	/* 
	   Path: /api/course/add
	   Spring has valid username, 
	   take it using Principal interface. 
	   this is admin username
	*/
	@PostMapping("/add")
	public Course postCourse(@RequestBody Course course){
		// Saving Trainer details
		Trainer trainer = course.getTrainer();
		trainer = trainerRepository.save(trainer);
		
		// saving Course details by Admin
		course.setTrainer(trainer);
		course = courseRepository.save(course);
		return course;
	}
	
	// Path : /api/course/all
	@GetMapping("/all")
	public List<CourseResponseDto> getAllCourse(){
		/* fetch all record from course table */
		List<Course> list = courseRepository.findAll(); 
		
		/* creating list to store all list of course */
		List<CourseResponseDto> listDto = new ArrayList<>();
		
		/* fetching single record and storing in list*/
		for(Course c: list) {
			CourseResponseDto dto = new CourseResponseDto();
			dto.setId(c.getId()); // (id)
			dto.setCourseName(c.getCourseName()); // (id, courseName)
			listDto.add(dto);
		}
		return listDto;
	}
	
	//Path: /api/course/trainer/all
	@GetMapping("/trainer/all")
	public List<CourseTrainerDto> getAllCourseWithTrainer(){
		/* fetch all record from course table */
		List<Course> list = courseRepository.findAll(); 
		
		/* creating list to store all list of course */
		List<CourseTrainerDto> listDto = new ArrayList<>();
		
		/* fetching single record and storing in list*/
		for(Course c: list) {
			CourseTrainerDto dto = new CourseTrainerDto();
			dto.setId(c.getId()); // (id)
			dto.setCourseName(c.getCourseName()); // (id, courseName)
			dto.setPrice(c.getPrice()); // (id, courseName, price)
			dto.setName(c.getTrainer().getName()); // (id, courseName, price, trainerName)
			listDto.add(dto);
		}
		return listDto;
	}
	
	@GetMapping("/all/{id}")
	public CourseTrainerDto getCourseById(@PathVariable("id") Long id) {
		Optional<Course> optional = courseRepository.findById(id);
		
		if(!optional.isPresent()) {
			throw new RuntimeException("ID is Invalid");
		}
		
		Course c = optional.get();
		
		CourseTrainerDto dto = new CourseTrainerDto();
		dto.setId(c.getId()); // (id)
		dto.setCourseName(c.getCourseName()); // (id, courseName)
		dto.setPrice(c.getPrice()); // (id, courseName, price)
		dto.setName(c.getTrainer().getName());
		
		return dto;
	}
}

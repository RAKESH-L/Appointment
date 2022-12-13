package com.springrest.appointment.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.appointment.dto.StudentResponseDto;
import com.springrest.appointment.model.Student;
import com.springrest.appointment.model.User;
import com.springrest.appointment.repository.AdminRepository;
import com.springrest.appointment.repository.StudentRepository;
import com.springrest.appointment.repository.UserRepository;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "http://localhost:8731/")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AdminRepository adminRepository;
	
	// Path: /api/student/add
	@PostMapping("/add")
	public ResponseEntity<String> addStudent(@RequestBody Student student){
		// Step 1: save User info in DB
		User user = student.getUser();
		user.setRole("STUDENT");
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userRepository.save(user); // id,username,password,role
		
		// Step 2: Attach User to Manager and Save Manager 
		student.setUser(user);
		student.setCreatedAt(LocalDate.now());
		student = studentRepository.save(student);
		student.getUser().setPassword("*******");
		return ResponseEntity.status(HttpStatus.OK).body("student Sign up Success");
	}
	
	// Path: /api/student/all
	@GetMapping("/all")
	public List<StudentResponseDto> getAllStudent(){
		List<Student> list = studentRepository.findAll();
		List<StudentResponseDto> listDto = new ArrayList<>();
		for(Student s : list) {
			StudentResponseDto dto = new StudentResponseDto();
			dto.setId(s.getId()); 
			dto.setName(s.getName());
			listDto.add(dto);
		}
		return listDto;
	}
}

package com.springrest.appointment.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.appointment.dto.AdminResponseDto;
import com.springrest.appointment.model.Admin;
import com.springrest.appointment.model.User;
import com.springrest.appointment.repository.AdminRepository;
import com.springrest.appointment.repository.UserRepository;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/add")
	public Admin postAdmin(@RequestBody Admin admin) {
		User user = admin.getUser();
		user.setRole("ADMIN");
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userRepository.save(user);
		
		admin.setUser(user);
		admin.setCreatedAt(LocalDate.now());
		admin = adminRepository.save(admin);
		admin.getUser().setPassword("*******");
		return admin;
	}
	@GetMapping("/all")
	public List<AdminResponseDto> getAllAdmin(){
		List<Admin> list = adminRepository.findAll();
		List<AdminResponseDto> listDto = new ArrayList<>();
		for(Admin a: list) {
			AdminResponseDto dto = new AdminResponseDto();
			dto.setId(a.getId());
			dto.setName(a.getName());
			listDto.add(dto);
		}
		return listDto;
	}
	
} 

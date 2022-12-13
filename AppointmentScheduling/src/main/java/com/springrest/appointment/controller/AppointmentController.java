package com.springrest.appointment.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.appointment.dto.AppointmentResponseDto;
import com.springrest.appointment.dto.AppointmentStatusResponseDto;
import com.springrest.appointment.enums.StudentAppointmentStatus;
import com.springrest.appointment.model.Appointment;
import com.springrest.appointment.model.Course;

import com.springrest.appointment.model.Slots;
import com.springrest.appointment.model.Student;
import com.springrest.appointment.model.Trainer;
import com.springrest.appointment.repository.AppointmentRepository;
import com.springrest.appointment.repository.CourseRepository;
import com.springrest.appointment.repository.SlotRepository;
import com.springrest.appointment.repository.StudentRepository;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private SlotRepository slotRepository;
	
	/*
	 	this are DI to send a mail to user
	 	JavaMailSender
	 	@Value("${spring.mail.username}")
	 */

	@Autowired 
	private JavaMailSender javaMailSender;
		 
	@Value("${spring.mail.username}") 
    private String sender;
	
	
	// Path: /api/appointment/add/{cid}
	@PostMapping("/add/{cid}/{sid}")
	public ResponseEntity<String> postAppointment(Principal principal,
												@RequestBody Appointment appointment,
												@PathVariable("cid") Long cid,
												@PathVariable("sid") Long sid){
		
		String username = principal.getName();
		
		/* Fetch Student details */
		Student student = studentRepository.getStudentByUsername(username);
		
		/* Attach this Student to appointment */
		appointment.setStudent(student);
		
		/*Fetch Course details*/
		Optional<Course> optional = courseRepository.findById(cid);
		
		/* Validate courseId */
		if(!optional.isPresent())
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course ID is Invalid");
		
		Optional<Slots> optional2 = slotRepository.findById(sid);
		
		if(!optional2.isPresent())
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Slot ID is Invalid");
		
		Course course = optional.get();
		/* Prepare Appointment Object */
		Slots slots = optional2.get();
		appointment.setCourse(course);
		appointment.setSlots(slots);
		appointment.setAppointmentDate(LocalDate.now());
		/* Set the status type */
		appointment.setStatus(StudentAppointmentStatus.PENDING);
		
		appointmentRepository.save(appointment);
		
		/*
		 	Sending Confirmation mail to user
		 	regarding Appointment Status
		 */
		// Creating a mail message
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		String msg = "Hi "+student.getName()+
				" Your appointment with Trainer "+course.getTrainer().getName()+
				" has been Booked with status "+StudentAppointmentStatus.PENDING+
				" Futher details with Course Name "+course.getCourseName()+
				" will be shared shortly"+
				" Thank You";
		// Setting up necessary details
		mailMessage.setFrom(sender);
		mailMessage.setTo(username);
		mailMessage.setText(msg);
		mailMessage.setSubject("Appointment Status of "+course.getCourseName());
		
		// Sending the mail
		javaMailSender.send(mailMessage);
		
		return ResponseEntity.status(HttpStatus.OK).body("Appointment Posted Successfully");
	}
	
	/* 
	 	Path: /api/appointment/all
	 	Display All Appointment of Particular Student
	 */
	@GetMapping("/all")
	public List<AppointmentResponseDto> listAllAppointment(Principal principal) {
		String username= principal.getName(); //Username of Student
		
		List<Appointment> appointments = appointmentRepository.getAllAppointment(username);
		
		/* Convert appointment into Dto */
		
		List<AppointmentResponseDto> listDto = new ArrayList<>();
		for(Appointment a : appointments) {
			//convert a to dto
			AppointmentResponseDto dto = new AppointmentResponseDto();
			dto.setId(a.getId());
			dto.setCourseName(a.getCourse().getCourseName());
			dto.setPrice(a.getCourse().getPrice());
			dto.setTrainerName(a.getCourse().getTrainer().getName());
			dto.setStartDate(a.getSlots().getStartDate());
			dto.setEndDate(a.getSlots().getEndDate());
			dto.setTime(a.getSlots().getTime());
			dto.setAppointmentDate(a.getAppointmentDate());
			dto.setStatus(a.getStatus());
			listDto.add(dto);
		}
		return listDto;
	}
	
	/* 
	 	Path: /api/appointment/status/{status}/{id}
	 	PENDING ---> APPROVED, DENIED
	 	update pending appointment to either APPROVED or DENIED,
	 	by Admin
	 */
	@PutMapping("/status/{status}/{id}") 
	public ResponseEntity<String> AppointmentStatusUpdate(
															@PathVariable("status") String status,
														  @PathVariable("id") Long appointmentId){
		
		/* Convert status to Enum*/
		StudentAppointmentStatus statusUpdate=null;
		try {
			statusUpdate =StudentAppointmentStatus.valueOf(status);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown Status");
		}
		/* Validate Appointment ID and fetch Appointment Details */
		Optional<Appointment> optional = appointmentRepository.findById(appointmentId);
		
		if(!optional.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Appointment ID is Invalid");
		
		Appointment appointment = optional.get();
		
		/* Update the status of this studentAppointment */
		appointment.setStatus(statusUpdate);
		
		/*Save the appointment */
		appointmentRepository.save(appointment);
		
		/*
	 	Sending Confirmation mail to user
	 	regarding Appointment Status
		 */
		// Creating a mail message
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		String username = appointment.getStudent().getUser().getUsername();
//		Course course = new Course();
//		Slots slots = new Slots();
		if(statusUpdate.toString() == "APPROVED") {
			mailMessage.setText("Hi "+appointment.getStudent().getName()+
					" Your Appointment with Course "+appointment.getCourse().getCourseName()+
					" has been '"+StudentAppointmentStatus.APPROVED+
					"' Please Attend Demo Training Session on "+appointment.getSlots().getStartDate()+
					" at '6 PM' For more details log in to our website");
		}
		if(statusUpdate.toString() == "DENIED") {
			mailMessage.setText("Hi "+appointment.getStudent().getName()+
					" Your Appointment with Course "+appointment.getCourse().getCourseName()+
					" has been '"+StudentAppointmentStatus.DENIED+
					" For more details log in to our website");
		}
		
		// Setting up necessary details
		mailMessage.setFrom(sender);
		mailMessage.setTo(appointment.getStudent().getUser().getUsername());
		
		mailMessage.setSubject("Appointment Status of "+appointment.getCourse().getCourseName());
		
		// Sending the mail
		javaMailSender.send(mailMessage);
		
		return ResponseEntity.status(HttpStatus.OK).body("Appointment Status Updated");
	}
	
	/*
	 	Path: /api/appointment/all/{status}
	 	Fetch all Pending Appointment Status where admin can APPROVE or DENY the request based on availability of seats
	 */
	@GetMapping("/all/{status}")
	public List<AppointmentStatusResponseDto> getPendingAppointment(Principal principal,
																	@PathVariable("status") String status){
		String username= principal.getName(); //Username of Admin
		
		/* Convert status into enum */
		StudentAppointmentStatus statusEnum = StudentAppointmentStatus.valueOf(status); //PENDING
		
		List<Appointment> appointments = appointmentRepository.getAllAppointmentByStatus(statusEnum);
		
		/* Convert appointments into Dto */
		List<AppointmentStatusResponseDto> listDto = new ArrayList<>();
		for(Appointment a : appointments) {
			// convert a to dto
			AppointmentStatusResponseDto dto = new AppointmentStatusResponseDto();
			dto.setId(a.getId());
			dto.setStudentName(a.getStudent().getName());
			dto.setCourseName(a.getCourse().getCourseName());
			dto.setAvailableSeats(a.getSlots().getAvailableSeats());
			dto.setTrainerName(a.getCourse().getTrainer().getName());
			dto.setStartDate(a.getSlots().getStartDate());
			dto.setTime(a.getSlots().getTime());
			listDto.add(dto);
		}
		return listDto;
	}
}

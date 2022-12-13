package com.springrest.appointment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.appointment.dto.SlotResponseDto;
import com.springrest.appointment.model.Course;
import com.springrest.appointment.model.Slots;
import com.springrest.appointment.repository.CourseRepository;
import com.springrest.appointment.repository.SlotRepository;

@RestController
@RequestMapping("/api/slot")
@CrossOrigin(origins = {"http://localhost:8731"})
public class SlotController {

	@Autowired
	private SlotRepository slotRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	/* Path: /api/slot/add/{id} 
	   Adding slot to particular course,
	   by assigning course ID to the API
	 */
	@PostMapping("/add/{id}")
	public Slots posrSlots(@RequestBody Slots slot,
							@PathVariable("id") Long id) {
		/*fetch Course details by ID and store in optional*/
		Optional<Course> optional = courseRepository.findById(id);
		
		/*If ID is not present throws exception*/
		if(!optional.isPresent()) {
			throw new RuntimeException("ID is Invalid");
		}
		Course c = optional.get();
		slot.setCourse(c);
		
		return slotRepository.save(slot);
	}
	
	// Path: /api/slot/course/{cid}
	@GetMapping("/course/{cid}")
	public List<SlotResponseDto> getSlotsByCourseId(@PathVariable("cid") Long cid){
		List<Slots> list = slotRepository.getSlotsByCourseId(cid);
		
		List<SlotResponseDto> listDto = new ArrayList<>();
		
		for(Slots s: list) {
			SlotResponseDto dto = new SlotResponseDto();
			dto.setId(s.getId());
			dto.setStartDate(s.getStartDate());
			dto.setEndDate(s.getEndDate());
			dto.setTime(s.getTime());
			dto.setAvailableSeats(s.getAvailableSeats());
			listDto.add(dto);
		}
		return listDto;
	}
}

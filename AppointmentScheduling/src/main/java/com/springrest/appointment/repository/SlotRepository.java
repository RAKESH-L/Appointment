package com.springrest.appointment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springrest.appointment.model.Slots;

public interface SlotRepository extends JpaRepository<Slots, Long>{

	@Query("select s from Slots s where s.course.id=?1")
	List<Slots> getSlotsByCourseId(Long cid);

}

package com.cinfy.mlearning.model.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cinfy.mlearning.model.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {

	List<Event> findAll();

	@SuppressWarnings("unchecked")
	Event save(Event event);

	void delete(Event event);

	@Query("select b from Event b " + "where b.start between ?1 and ?2 and b.end between ?1 and ?2")
	List<Event> findByDatesBetween(Date start, Date end);

}

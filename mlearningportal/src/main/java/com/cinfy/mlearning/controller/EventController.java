/*package com.cinfy.mlearning.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cinfy.mlearning.model.Event;
import com.cinfy.mlearning.model.request.BadDateFormatException;
import com.cinfy.mlearning.model.repositories.EventRepository;

@RestController
public class EventController {

	@Autowired
	EventRepository eventRepository;
	
	@RequestMapping(value = "/calendar", method = RequestMethod.GET)
	public ModelAndView calender(Model model) {

		
		return new ModelAndView("jsoncalendar");
	}
	
	

	@RequestMapping(value = "/allevents", method = RequestMethod.GET)
	public List<Event> allEvents() {
		List<Event> li = null;
		try {
			System.out.println("Calender all events");
			li = eventRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return li;
	}

	@RequestMapping(value = "/addevent", method = RequestMethod.POST)
	public Event addEvent(@RequestBody Event event) {
		Event created = eventRepository.save(event);
		return created;
	}

	@RequestMapping(value = "/updateevent", method = RequestMethod.PATCH)
	public Event updateEvent(@RequestBody Event event) {
		return eventRepository.save(event);
	}

	@RequestMapping(value = "/removeevent", method = RequestMethod.DELETE)
	public void removeEvent(@RequestBody Event event) {
		eventRepository.delete(event);
	}

	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public List<Event> getEventsInRange(@RequestParam(value = "start", required = true) String start,
			@RequestParam(value = "end", required = true) String end) {
		Date startDate = null;
		Date endDate = null;
		SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			startDate = inputDateFormat.parse(start);
		} catch (ParseException e) {
			throw new BadDateFormatException("bad start date: " + start);
		}

		try {
			endDate = inputDateFormat.parse(end);
		} catch (ParseException e) {
			throw new BadDateFormatException("bad end date: " + end);
		}

		return eventRepository.findByDatesBetween(startDate, endDate);
	}

}
*/
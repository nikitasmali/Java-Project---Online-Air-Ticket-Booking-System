package com.airline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.dto.TicketBookingDTO;
import com.airline.entity.TicketBooking;
import com.airline.service.TicketBookingService;
import com.airline.util.TicketConverter;

@RestController
@RequestMapping("/api")
public class TicketController {

	@Autowired
	TicketBookingService ticketBookingService;
	
	@Autowired
	TicketConverter ticketConverter;
	
	//build ticket booking using REST API
	@PostMapping("/bookFlight/{fid}/{pid}")
	public String bookFlight(@PathVariable("fid") int fid,
			@PathVariable("pid") int pid,
			@RequestBody TicketBookingDTO ticketBookingDTO)
	{
		final TicketBooking booking = ticketConverter.convertToTicketEntity(ticketBookingDTO);
		return ticketBookingService.bookFlight(fid, pid, booking);
	}
	
	//cancel booking using id
	@DeleteMapping("/cancelBooking/{id}")
	public String cancelBooking(@PathVariable("id") int id)
	{
		return ticketBookingService.cancelBooking(id);
	}
}

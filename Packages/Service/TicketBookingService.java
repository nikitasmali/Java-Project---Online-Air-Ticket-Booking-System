package com.airline.service;


import com.airline.entity.TicketBooking;

public interface TicketBookingService {

	String bookFlight(int fid, int pid, TicketBooking ticketBooking);
	String cancelBooking(int id);
}

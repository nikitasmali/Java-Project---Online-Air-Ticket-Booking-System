package com.airline.serviceimpl;


import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.entity.Flight;
import com.airline.entity.Passenger;
import com.airline.entity.TicketBooking;
import com.airline.entity.TicketGenerationPdf;
import com.airline.exception.ResourceNotFoundException;
import com.airline.repository.FlightRepository;
import com.airline.repository.PassengerRepository;
import com.airline.repository.TicketRepository;
import com.airline.service.TicketBookingService;
import com.airline.util.FlightConverter;
import com.airline.util.TicketConverter;

@Service
public class TicketBookingServiceImpl implements TicketBookingService {

	private static final Logger l = LoggerFactory.getLogger(TicketBooking.class);
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	FlightConverter flightConverter;
	
	@Autowired
	TicketConverter ticketConverter;
	
	@Autowired
	TicketGenerationPdf ticketGenerationPdf;

	@Override
	public String bookFlight(int fid, int pid, TicketBooking ticketBooking) {
		String message=null;
		l.info("Passenger is booking a flight at "+new Date());
		Flight flight =flightRepository.findById(fid).get();
		Passenger passenger = passengerRepository.findById(pid).get();
		
		if(flight.getSource().equals(ticketBooking.getSource())
				&& flight.getDestination().equals(ticketBooking.getDestination()))
		{
			LocalDate fDate = flight.getDate();
			LocalDate bookingDate = LocalDate.now();
			if(!bookingDate.isAfter(fDate))
			{
				int total_seat= flight.getAvailableSeats()- ticketBooking.getNoOfPassenger();
				flight.setAvailableSeats(total_seat);
				
				ticketBooking.setTotalFare(flight.getAirline().getFare()
						*ticketBooking.getNoOfPassenger());
				ticketBooking.setFId(flight);
				ticketBooking.setPId(passenger);
				ticketBooking.setAId(ticketBooking.getFId().getAirline());
				TicketBooking booked = ticketRepository.save(ticketBooking);
			
				if(booked!=null)
				message="Your ticket has been booked successfully!!";
				ticketGenerationPdf.TicketGeneration(booked);
			
			}
			else
			{
				message="You have to book before travel date.";
			}
				}
				else
					message="Please enter correct source and destination!!";
			return message;
	}

	@Override
	public String cancelBooking(int id) {
		String msg=null;
		Optional<TicketBooking> ticket = ticketRepository.findById(id);
		if(ticket.isPresent())
		{
			int nop = ticket.get().getNoOfPassenger();
			ticket.get().getFId().setAvailableSeats((ticket.get().getFId().getAvailableSeats() + nop));
			ticketRepository.deleteById(id);
			msg="Your booking is cancelled!!";
		}
		else
			throw new ResourceNotFoundException("Ticket", "id", id);
		return msg;
	}
	
	

}

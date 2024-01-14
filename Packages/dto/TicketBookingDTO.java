package com.airline.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.airline.entity.Airline;
import com.airline.entity.Flight;
import com.airline.entity.Passenger;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TicketBookingDTO {
	
private int ticketId;
	
	@NotNull(message="Number of Passengers field can't be null")
	private int noOfPassenger;
	
	private double totalFare;
	
	@NotNull(message="Date field can't be null")
	private LocalDate date= LocalDate.now();

	@NotNull(message="Source field can't be null")
	private String source;
	
	@NotNull(message="Destination field can't be null")
	private String destination;

	private Flight flightId;
	
	private Airline airlineId;
	
	private Passenger passengerId;
	
}

package com.airline.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.airline.entity.Airline;

import lombok.Data;

@Data
public class FlightDTO {
	
	private int flightId;

	@NotNull(message="Available seats can't be null")
	private int availableSeats;

	@NotNull(message="Total seats can't be null")
	private int totalSeats;
	
	@NotNull(message="Traveller class can't be null")
	private String travellerClass;

	@NotNull(message="Time can't be null")
	private String time;

	@NotNull(message="Date can't be null")
	private LocalDate date;

	@NotNull(message="Source can't be null")
	private String source;
	
	@NotNull(message="Destination can't be null")
	private String destination;

	private Airline airline;
}

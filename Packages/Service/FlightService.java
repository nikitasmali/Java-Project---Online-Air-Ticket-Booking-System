package com.airline.service;

import java.util.List;

import com.airline.dto.FlightDTO;
import com.airline.entity.Flight;

public interface FlightService {
	FlightDTO saveFlight(Flight flight);
	List<FlightDTO> searchFlight(String source, String destination);
	FlightDTO updateFlight(int id, Flight flight);
	List<FlightDTO> getAllFlights();
	FlightDTO getFlightById(int id);
	String deleteFlightById(int id);
	FlightDTO assignFlightToAirline(int flightId, int airlineId);
}

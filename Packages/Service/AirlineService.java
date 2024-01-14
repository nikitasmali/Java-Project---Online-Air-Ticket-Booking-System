package com.airline.service;

import java.util.List;

import com.airline.dto.AirlineDTO;
import com.airline.entity.Airline;

public interface AirlineService {

	AirlineDTO saveAirline(Airline airline);
	 List<AirlineDTO> getAirlineByName(String name);
	 List<AirlineDTO> getAllAirline();
	 AirlineDTO updateAirline(int id, Airline airline);
	 String deleteAirlineById(int id);
	 
}

package com.airline.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.dto.FlightDTO;
import com.airline.entity.Airline;
import com.airline.entity.Flight;
import com.airline.exception.ResourceNotFoundException;
import com.airline.repository.AirlineRepository;
import com.airline.repository.FlightRepository;
import com.airline.service.FlightService;
import com.airline.util.FlightConverter;

@Service
public class FlightServiceImpl implements FlightService{

	private static final Logger l = LoggerFactory.getLogger(Flight.class);
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	AirlineRepository airlineRepository;
	
	@Autowired
	FlightConverter flightConverter;
	
	@Override
	public FlightDTO saveFlight(Flight flight) {
		
		Flight f= flightRepository.save(flight);
		l.info("Flight "+flight.toString()+ " added at "+new Date() );
		return flightConverter.convertToFlightDTO(f);
	}

	@Override
	public List<FlightDTO> searchFlight(String source, String destination) {
		List<Flight> flight= flightRepository.searchFlight(source, destination);
		l.info("Searching flight using destination and source at "+new Date());
		List<FlightDTO> fDTO = new ArrayList<>();
		for(Flight f: flight)
		{
			fDTO.add(flightConverter.convertToFlightDTO(f));
		}
		return fDTO;
	}

	@Override
	public FlightDTO updateFlight(int id, Flight flight) {
		Flight fli = flightRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Flight", "id", id));
		
		fli.setTotalSeats(flight.getTotalSeats());
		fli.setAvailableSeats(flight.getAvailableSeats());
		fli.setTravellerClass(flight.getTravellerClass());
		fli.setSource(flight.getSource());
		fli.setDestination(flight.getDestination());
		fli.setDate(flight.getDate());
		fli.setTime(flight.getTime());
		
		flightRepository.save(fli);
		l.info("Updating flight details of "+id+" at "+new Date());
		return flightConverter.convertToFlightDTO(fli);
	}

	@Override
	public List<FlightDTO> getAllFlights() {
		List<Flight> flights= flightRepository.findAll();
		l.info("Fetching all flight details at "+new Date() );
		List<FlightDTO> flightDTO=new ArrayList<>();
		for(Flight fli: flights)
		{
			flightDTO.add(flightConverter.convertToFlightDTO(fli));
		}
		return flightDTO;
	}

	@Override
	public String deleteFlightById(int id) {
		String msg =null;
		l.info("Deleting flight details of "+id+" at "+new Date());
		Optional<Flight> opFli =flightRepository.findById(id);
		if(opFli.isPresent())
		{
			flightRepository.deleteById(id);
			msg="Flight record deleted successfully!!";
		}
		else
		{
			throw new ResourceNotFoundException("Flight", "ID", id);
		}
		return msg;
	}

	@Override
	public FlightDTO assignFlightToAirline(int flightId, int airlineId) {
		Airline airline = airlineRepository.findById(airlineId).get();
		Flight flight = flightRepository.findById(flightId).get();
		flight.setAirline(airline);
		Flight f =flightRepository.save(flight);
		l.info("Assigning Flight"+flight.getFlightId()+ " to Airline "+airline.getAirlineName()+" at "+new Date());
		return flightConverter.convertToFlightDTO(f);
	}

	@Override
	public FlightDTO getFlightById(int id) {
		Flight fli = flightRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Flight", "Id", id));
		l.info("Getting flight details of "+id+" at "+new Date());
		return flightConverter.convertToFlightDTO(fli);
	}
	
	

}

package com.airline.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.dto.FlightDTO;
import com.airline.entity.Flight;
import com.airline.service.FlightService;
import com.airline.util.FlightConverter;


@RestController
@RequestMapping("/api")
public class FlightController {

	@Autowired
	private FlightService flightService;
	
	@Autowired
	private FlightConverter flightConverter;
	
	//build save flight REST API
	@PostMapping("/saveFlight/{role}")
	public ResponseEntity<?> saveFlight(@PathVariable("role") String role,@RequestBody FlightDTO flightDTO)
	{
		final Flight flight = flightConverter.convertToFlightEntity(flightDTO);
		if(role.equals("admin"))
		{
		return new ResponseEntity<FlightDTO>(flightService.saveFlight(flight),
				HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity<String>("You are not admin",HttpStatus.BAD_REQUEST);
		}
	}
	
	
	//build to GET flight using fields source and destination REST API
	@GetMapping("/searchFlight/{source}/{destination}")
	public List<FlightDTO> searchFlight(@PathVariable("source") String source,
			@PathVariable("destination") String destination)
	{
		return flightService.searchFlight(source, destination);
	}
	
	
	//build update flight REST API
	@PutMapping("/updateFlight/{id}/{role}")
	public ResponseEntity<?> updateFlight(@PathVariable("role") String role,@PathVariable("id") int id,
			@RequestBody FlightDTO flightDTO)
	{
		if(role.equals("admin"))
		{
		final Flight flight = flightConverter.convertToFlightEntity(flightDTO);
		return new ResponseEntity<FlightDTO>(flightService.updateFlight(id, flight),
				HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("You are not admin",HttpStatus.BAD_REQUEST);
		}
	}
	
	
	//build GET ALL flights REST API
	@GetMapping("/getAllFlights")
	public List<FlightDTO> getAllFLights()
	{
		return flightService.getAllFlights();
	}
	
	
	//build Delete by id flight REST API
	@DeleteMapping("/deleteFlightById/{id}/{role}")
	public ResponseEntity<?> deleteFlightById(@PathVariable("role") String role,@PathVariable("id") int id)
	{
		if(role.equals("admin"))
		{
			return new ResponseEntity<>(flightService.deleteFlightById(id),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("You are not admin",HttpStatus.BAD_REQUEST);
		}

	}
	
	//build Assign Flight to airline REST API
	@PostMapping("assignFlight/{fid}/{aid}/{role}")
	public ResponseEntity<?> assignFlighToAirline(@PathVariable("role") String role,@PathVariable("fid") int flightId,
			@PathVariable("aid") int airlineId)
	{
		if(role.equals("admin"))
		{
		return new ResponseEntity<FlightDTO>(flightService.assignFlightToAirline(flightId, airlineId),
				HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("You are not admin",HttpStatus.BAD_REQUEST);
		}
	}
	
	//build get flight details using id REST API
	@GetMapping("/getFlightById/{id}")
	public FlightDTO getFlightById(@PathVariable("id") int id)
	{
		return flightService.getFlightById(id);
	}
}

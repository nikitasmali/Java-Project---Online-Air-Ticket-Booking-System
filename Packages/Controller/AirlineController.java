package com.airline.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
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

import com.airline.dto.AirlineDTO;
import com.airline.entity.Airline;
import com.airline.service.AirlineService;
import com.airline.util.AirlineConverter;

@RestController
@RequestMapping("/api")
public class AirlineController {

	@Autowired
	private AirlineService airlineService;
	
	@Autowired
	private AirlineConverter airlineConverter;
	
	//build SAVE AIRLINE REST API
	@PostMapping("/saveAirline/{role}")
	public ResponseEntity<?> saveAirline(@PathVariable("role") String role,
			@RequestBody AirlineDTO airlineDTO)
	{
		if(role.equals("admin"))
		{
		final Airline airline = airlineConverter.convertToAirlineEntity(airlineDTO);
		return new ResponseEntity<AirlineDTO>(airlineService.saveAirline(airline),
				HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity<String>("You are not admin",HttpStatus.BAD_REQUEST);
		}
	}
	
	//build get airline using name REST API
		@GetMapping("/getAirlineByName/{name}")
		public List<AirlineDTO> getAirlineByName(@PathVariable("name") String name)
		{
			return airlineService.getAirlineByName(name);
		}
		
	//build get all airlines REST API
	@GetMapping("/getAllAirline")
	public List<AirlineDTO> getAllAirline()
	{
		return airlineService.getAllAirline();
	}
	
	//build update airline REST API
	@PutMapping("/updateAirline/{id}/{role}")
	public ResponseEntity<?> updateAirline(@PathVariable("role") String role,
			@PathVariable("id") int id,
			@RequestBody AirlineDTO airlineDTO)
	{
		if(role.equals("admin"))
		{
		final Airline airline = airlineConverter.convertToAirlineEntity(airlineDTO);
		return new ResponseEntity<AirlineDTO>(airlineService.updateAirline(id, airline),
				HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("You are not admin",HttpStatus.BAD_REQUEST);
		}
	}
	
	//build Delete by id airline REST API
		@DeleteMapping("/deleteAirlineById/{id}/{role}")
		public ResponseEntity<?> deleteAirlineById(@PathVariable("role") String role,
				@PathVariable int id)
		{
			if(role.equals("admin"))
			{
				return new ResponseEntity<>(airlineService.deleteAirlineById(id),HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<String>("You are not admin",HttpStatus.BAD_REQUEST);
			}
		}
}

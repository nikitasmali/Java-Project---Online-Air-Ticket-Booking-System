package com.airline.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.airline.dto.PassengerDTO;
import com.airline.entity.Passenger;
import com.airline.service.PassengerService;
import com.airline.util.Converter;

@RestController
@RequestMapping("/api")
public class PassengerController {

	@Autowired
	private PassengerService passengerService;
	
	@Autowired
	Converter converter;
	
	
	//build create passenger REST API
	@PostMapping("/createPassenger")
	public String createPassenger(@Valid @RequestBody PassengerDTO passengerDTO)
	{
		final Passenger passenger= converter.convertToPassengerEntity(passengerDTO);
		return passengerService.createPassenger(passenger);
	}
	
	//build update passenger REST API
	//localhost:8806/updatePassenger/2
	@PutMapping("/updatePassenger/{id}")
	public ResponseEntity<PassengerDTO> updatePassenger(@Valid @PathVariable("id") int id,
			@RequestBody PassengerDTO passengerDTO)
	{
		final Passenger passenger= converter.convertToPassengerEntity(passengerDTO);
		return new ResponseEntity<PassengerDTO>(passengerService.updatePassenger(id, passenger),
				HttpStatus.OK);
	}
	
	//build get passenger REST API
	@GetMapping("/getPassengerById/{id}")
	public PassengerDTO getPassengerById(@PathVariable("id") int id)
	{
		return passengerService.getPassengerById(id);
	}
	
	//build get all passengers REST API
	@GetMapping("/getAllPassenger")
	public List<PassengerDTO> getAllPassenger()
	{
		return passengerService.getAllPassenger();
	}
	
	//build DELETE passenger by ID REST API
	@DeleteMapping("/deletePassengerById/{id}")
	public String deletePassengerById(@PathVariable int id)
	{
		return passengerService.deletePassengerById(id);
	}
	
	//build DELETE ALL passenger REST API
	@DeleteMapping("/deleteAllPassengers")
	public ResponseEntity<String> deleteAllPassengers()
	{
		passengerService.deleteAllPassengers();
		return new ResponseEntity<String>("All passenger details have been deleted!!",
				HttpStatus.OK);
	}
	
	
	//build get passenger using name REST API
	@GetMapping("/getPassengerByName/{name}")
	public List<PassengerDTO> getPassengerByName(@PathVariable("name") String name)
	{
		return passengerService.getPassengerByName(name);
	}
	
	//build get passenger using email REST API
		@GetMapping("/getPassengerByEmail/{email}")
		public PassengerDTO getPassengerByEmail(@PathVariable("email") String email)
		{
			return passengerService.getPassengerByEmail(email);
		}
	
	
}

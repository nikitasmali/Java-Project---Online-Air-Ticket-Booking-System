package com.airline.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.dto.PassengerDTO;
import com.airline.entity.Passenger;
import com.airline.exception.ResourceNotFoundException;
import com.airline.repository.PassengerRepository;
import com.airline.service.PassengerService;
import com.airline.util.Converter;

@Service
public class PassengerServiceImpl implements PassengerService {

	//logger statically created
	private static final Logger l = LoggerFactory.getLogger(Passenger.class);
	
	@Autowired
	private PassengerRepository passengerRepository;
	
	@Autowired
	private Converter converter;
	
	@Override
	public String createPassenger(Passenger passenger) {
		String message = null;
		
		passenger.setUserName(passenger.getUserName());
		passenger.setPassword(passenger.getPassword());
		passenger.setRole(passenger.getRole());
		
		passengerRepository.save(passenger);
		l.info("Passenger"+passenger.toString()+" added at "+ new Date());
		if(passenger!=null)
		{
			message="Passenger saved successfully!!";
		}
		return message;
	}

	@Override
	public PassengerDTO updatePassenger(int id, Passenger passenger) {
		//we need to check whether passenger with id is present in DB or not
		Passenger existingPass= passengerRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Passenger", "id", id));
		
		//we will get data from client and set in existing passenger
		existingPass.setName(passenger.getName());
		existingPass.setPhno(passenger.getPhno());
		existingPass.setEmail(passenger.getEmail());
		existingPass.setUserName(passenger.getUserName());
		existingPass.setPassword(passenger.getPassword());
		existingPass.setRole(passenger.getRole());
		
		passengerRepository.save(existingPass);
		l.info("Passenger with "+id+" updated at" + new Date());
		return converter.convertToPassengerDTO(existingPass);
	}

	@Override
	public PassengerDTO getPassengerById(int id) {
		
		Passenger pass= passengerRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Passenger", "id", id));
		l.info("Getting passenger details of "+id+" at "+new Date());
		return converter.convertToPassengerDTO(pass);
	}

	@Override
	public List<PassengerDTO> getAllPassenger() {
		List<Passenger> passengers= passengerRepository.findAll();
		l.info("Getting all passenger at "+ new Date());
		List<PassengerDTO> passengersDTO=new ArrayList<>();
		for(Passenger pass: passengers)
		{
			passengersDTO.add(converter.convertToPassengerDTO(pass));
		}
		return passengersDTO;
	}

	@Override
	public String deletePassengerById(int id) {
		String msg =null;
		l.info("Deleting passenger details with id" +id+ " at "+new Date());
		Optional<Passenger> opPass =passengerRepository.findById(id);
		if(opPass.isPresent())
		{
			passengerRepository.deleteById(id);
			msg="Record deleted successfully";
		}
		else
		{
			throw new ResourceNotFoundException("Passenger", "ID", id);
		}
		return msg;
	}

	@Override
	public void deleteAllPassengers() {
		l.info("Deleting all passenger details at "+new Date());
		passengerRepository.deleteAll();
	}

	@Override
	public List<PassengerDTO> getPassengerByName(String name) {
		List<Passenger> passengers = passengerRepository.getPassengerByName(name);
		l.info("Getting passenger details using name at "+new Date());
		List<PassengerDTO> pDTO = new ArrayList<>();
		for(Passenger p: passengers)
		{
			pDTO.add(converter.convertToPassengerDTO(p));
		}
		return pDTO;
	}

	@Override
	public PassengerDTO getPassengerByEmail(String email) {
		Passenger pass = passengerRepository.getPassengerByEmail(email);
		l.info("Getting passenger details using email at "+new Date());
		return converter.convertToPassengerDTO(pass);
	}

}

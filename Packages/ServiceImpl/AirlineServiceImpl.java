package com.airline.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.dto.AirlineDTO;
import com.airline.entity.Airline;
import com.airline.exception.ResourceNotFoundException;
import com.airline.repository.AirlineRepository;
import com.airline.service.AirlineService;
import com.airline.util.AirlineConverter;

@Service
public class AirlineServiceImpl implements AirlineService{

	private static final Logger l = LoggerFactory.getLogger(Airline.class);
	
	@Autowired
	AirlineRepository airlineRepository;
	
	@Autowired
	AirlineConverter airlineConverter;
	
	@Override
	public AirlineDTO saveAirline(Airline airline) {
		l.info("Airline "+airline.toString()+" added at "+new Date());
		Airline air = airlineRepository.save(airline);
		return airlineConverter.convertToAirlineDTO(air);
	}

	@Override
	public List<AirlineDTO> getAirlineByName(String name) {
		List<Airline> airline = airlineRepository.getAirlineByName(name);
		l.info("Getting airline details using name "+ new Date());
		List<AirlineDTO> aDTO = new ArrayList<>();
		for(Airline a: airline)
		{
			aDTO.add(airlineConverter.convertToAirlineDTO(a));
		}
		return aDTO;
	}

	@Override
	public List<AirlineDTO> getAllAirline() {
		List<Airline> airlines = airlineRepository.findAll();
		l.info("Fetching all airline details at "+ new Date() );
		List<AirlineDTO> airlinesDTO = new ArrayList<>();
		for(Airline air: airlines)
		{
			airlinesDTO.add(airlineConverter.convertToAirlineDTO(air));
		}
		return airlinesDTO;
	}

	@Override
	public AirlineDTO updateAirline(int id, Airline airline) {
		Airline air = airlineRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Airline", "id", id));
		
		air.setAirlineName(airline.getAirlineName());
		air.setFare(airline.getFare());
		
		airlineRepository.save(air);
		l.info("Updating airline details of "+id+" at "+new Date() );
		return airlineConverter.convertToAirlineDTO(air);
	}

	@Override
	public String deleteAirlineById(int id) {
		String msg =null;
		l.info("Deleting airline details of "+id+ " at " + new Date());
		Optional<Airline> opAir =airlineRepository.findById(id);
		if(opAir.isPresent())
		{
			airlineRepository.deleteById(id);
			msg="Airline record deleted successfully";
		}
		else
		{
			throw new ResourceNotFoundException("Airline", "ID", id);
		}
		return msg;
	}

	
}

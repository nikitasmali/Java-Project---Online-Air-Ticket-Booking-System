package com.airline.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.airline.dto.AirlineDTO;
import com.airline.entity.Airline;

@Component
public class AirlineConverter {

	// convert from airlineDTO to Entity(Airline)
			public Airline convertToAirlineEntity(AirlineDTO airlineDTO)
			{
				Airline airline = new Airline();
				if(airlineDTO!=null)
				{
					BeanUtils.copyProperties(airlineDTO, airline);
				}
				return airline;
			}
			
			//convert from airline entity to AirlineDTO
			public AirlineDTO convertToAirlineDTO(Airline airline)
			{
				AirlineDTO airlineDTO = new AirlineDTO();
				if(airline!=null)
				{
					BeanUtils.copyProperties(airline, airlineDTO);
				}
				return airlineDTO;
			}
}

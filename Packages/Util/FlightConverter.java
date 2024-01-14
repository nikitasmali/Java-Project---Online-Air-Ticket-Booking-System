package com.airline.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.airline.dto.FlightDTO;
import com.airline.entity.Flight;

@Component
public class FlightConverter {

	// convert from flightDTO to Entity(Flight)
		public Flight convertToFlightEntity(FlightDTO flightDTO)
		{
			Flight flight = new Flight();
			if(flightDTO!=null)
			{
				BeanUtils.copyProperties(flightDTO, flight);
			}
			return flight;
		}
		
		//convert from flight entity to FlightDTO
		public FlightDTO convertToFlightDTO(Flight flight)
		{
			FlightDTO flightDTO = new FlightDTO();
			if(flight!=null)
			{
				BeanUtils.copyProperties(flight, flightDTO);
			}
			return flightDTO;
		}
}

package com.airline.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.airline.dto.AdminDTO;
import com.airline.dto.PassengerDTO;
import com.airline.entity.Admin;
import com.airline.entity.Passenger;

@Component
public class Converter {

	// convert from passengerDTO to Entity(Passenger)
	public Passenger convertToPassengerEntity(PassengerDTO passengerDTO)
	{
		Passenger passenger = new Passenger();
		if(passengerDTO!=null)
		{
			BeanUtils.copyProperties(passengerDTO, passenger);
		}
		return passenger;
	}
	
	//convert from passenger entity to PassengerDTO
	public PassengerDTO convertToPassengerDTO(Passenger passenger)
	{
		PassengerDTO passengerDTO = new PassengerDTO();
		if(passenger!=null)
		{
			BeanUtils.copyProperties(passenger, passengerDTO);
		}
		return passengerDTO;
	}
	
	//convert from adminDTO to Entity(Admin)
	public Admin convertToAdminEntity(AdminDTO adminDTO)
	{
		Admin admin = new Admin();
		if(adminDTO!=null)
		{
			BeanUtils.copyProperties(adminDTO, admin);
		}
		return admin;
	}
	
	//convert from admin entity to AdminDTO
	public AdminDTO convertToAdminDTO(Admin admin)
	{
		AdminDTO adminDTO = new AdminDTO();
		if(admin!=null)
		{
			BeanUtils.copyProperties(admin, adminDTO);
		}
		return adminDTO;
	}
}

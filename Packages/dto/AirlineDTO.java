package com.airline.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.airline.entity.Flight;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AirlineDTO {

	private int id;

	@NotNull(message="Airline name can't be null")
	private String airlineName;

	@NotNull(message="Fare can't be null")
	private float fare;

	List<Flight> flights;
}

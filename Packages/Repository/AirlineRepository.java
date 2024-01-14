package com.airline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.airline.entity.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Integer>{
	
	@Query("from Airline where airlineName=:a")
	List<Airline> getAirlineByName(@Param("a") String name);
}

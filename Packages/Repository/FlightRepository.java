package com.airline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.airline.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer>{

	@Query("from Flight where source=:s and destination=:d")
	List<Flight> searchFlight(@Param("s") String source,@Param("d") String destination);
}

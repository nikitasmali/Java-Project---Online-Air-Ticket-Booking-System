package com.airline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.airline.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

	@Query("select p from Passenger p where p.name=?1")
	List<Passenger> getPassengerByName(String name);
	
	@Query("from Passenger where email=:e")
	Passenger getPassengerByEmail(@Param("e") String email);
}

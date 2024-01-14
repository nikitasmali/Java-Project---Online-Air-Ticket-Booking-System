package com.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airline.entity.TicketBooking;

public interface TicketRepository extends JpaRepository<TicketBooking, Integer>{

}

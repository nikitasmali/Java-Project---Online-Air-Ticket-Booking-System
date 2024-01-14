package com.airline.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "booking_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketBooking {
	
	@Id	
	@GenericGenerator(name = "ticket_no" , strategy = "com.airline.entity.TicketNoGenerator")
	@GeneratedValue(generator = "ticket_no")
	private int ticketId;
	@Column(name ="total_passenger",length = 9)
	private int noOfPassenger;

	@Column(length = 200)
	private float totalFare;

	@Column(name = "travel_date")
	private LocalDate date=LocalDate.now();

	private String source;
	
	private String destination;
		

	@OneToOne
	private Flight fId;

	@OneToOne
	private Airline aId;

	@OneToOne
	private Passenger pId;

}

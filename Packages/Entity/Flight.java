package com.airline.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int flightId;

	@Column(name = "available_seats",nullable = false)
	private int availableSeats;

	@Column(name = "total_seats",nullable = false)
	private int totalSeats;

	@Column(length = 50,nullable = false)
	private String travellerClass;
	
     @Column(nullable = false)
	private String time;
     
     @Column(nullable = false)
	private LocalDate date;

	@Column(length = 50,nullable = false)
	private String source;
	
	@Column(length = 50,nullable = false)
	private String destination;

	@ManyToOne
	@JoinColumn(name="airline_id")
	@JsonIgnoreProperties("flights")
	private Airline airline;

}

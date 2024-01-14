package com.airline.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Airline {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "airline_name",length = 50, nullable=false)
	private String airlineName;

	@Column(nullable=false)
	private float fare;
	
	@OneToMany(mappedBy = "airline", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("airline")
	private List<Flight> flights;

	@Builder
	public Airline(int id, String airlineName, float fare) 
	{
		super();
		this.id = id;
		this.airlineName = airlineName;
		this.fare = fare;
	}
}

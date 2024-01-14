package com.airline.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class PassengerDTO extends UserDTO{

	@NotNull
	@Size(min=2, message=" name should have minimum 2 characters")
	private String name;
	
	@NotNull
	@Pattern(regexp = "^\\d{10}$", message = "Phone number should have 10 digits")
	private String phno;

	@NotNull
	@Email
	private String email;
}

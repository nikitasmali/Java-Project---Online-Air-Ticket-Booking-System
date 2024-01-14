package com.airline.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminDTO extends UserDTO{
	
	@NotNull
	@Size(min=2, message=" name should have minimum 2 characters")
	private String name;

	@NotNull
	@Email
	private String email;
}

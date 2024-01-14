package com.airline.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class UserDTO {
	
	private int id;
	
	@NotNull
	@Size(min=2, message= "username should have minimum 2 characters")
	private String UserName;
	
	@NotNull
	@Size(min=2, message= "password should have minimum 2 characters")
	private String password;
	
	@NotNull
	private String role;
}

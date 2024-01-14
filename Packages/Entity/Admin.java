package com.airline.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin extends User{

	@Column(length = 50, nullable = false)
	private String name;
	
    @Column(length = 100, nullable= false, unique= true)
	private String email;

    @Builder
	public Admin(int id, String userName, String password, String role, String name, String email) {
		super(id, userName, password, role);
		this.name = name;
		this.email = email;
	}
}

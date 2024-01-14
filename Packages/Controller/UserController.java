package com.airline.controller;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.entity.User;
import com.airline.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping(path="/login")
	public String validate(@RequestBody User user) throws ServletException
	{
		String jwtToken = "";
		if(user.getUserName()==null || user.getPassword()==null || user.getRole()==null)
		{
			throw new ServletException("Please fill in username, password and role");	
		}
		String userName= user.getUserName();
		String password = user.getPassword();
		user=userService.login(userName, password);
		
		if(user==null)
		{
			throw new ServletException("User details not found!");
		}
	
		jwtToken=Jwts.builder().setSubject(userName).claim("roles", user.getRole()).
		setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		
		return jwtToken;
	}
}

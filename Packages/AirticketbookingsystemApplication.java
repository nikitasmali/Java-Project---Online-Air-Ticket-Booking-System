package com.airline;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.airline.config.JwtFilter;

//https://jwt.io/#debugger-io

@SpringBootApplication
public class AirticketbookingsystemApplication {

	@SuppressWarnings({"unchecked","rawtypes"})
	
	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		final FilterRegistrationBean registrationBean= new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/api/*");
		return registrationBean;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AirticketbookingsystemApplication.class, args);
		System.out.println("Welcome To Airline Ticket Booking System");
	}
}

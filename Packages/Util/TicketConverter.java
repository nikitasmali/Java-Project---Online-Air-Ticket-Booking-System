package com.airline.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.airline.dto.TicketBookingDTO;
import com.airline.entity.TicketBooking;

@Component
public class TicketConverter {

	// convert from ticketBookingDTO to Entity(Ticket)
			public TicketBooking convertToTicketEntity(TicketBookingDTO ticketBookingDTO)
			{
				TicketBooking ticket = new TicketBooking();
				if(ticketBookingDTO!=null)
				{
					BeanUtils.copyProperties(ticketBookingDTO, ticket);
				}
				return ticket;
			}
			
			//convert from Ticket entity to TicketBookingDTO
			public TicketBookingDTO convertToTicketDTO(TicketBooking ticketBooking)
			{
				TicketBookingDTO ticketDTO = new TicketBookingDTO();
				if(ticketBooking!=null)
				{
					BeanUtils.copyProperties(ticketBooking, ticketDTO);
				}
				return ticketDTO;
			}
}

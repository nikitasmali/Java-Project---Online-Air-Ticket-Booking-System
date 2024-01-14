package com.airline.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

//this class will generate unique account number
public class TicketNoGenerator implements IdentifierGenerator {

	public int generateTicketId()
	{
		Random random=new Random();
		return random.nextInt(99999);
	
	}
	
	
	//for unique ticket id,added 556 and calendar year with random number
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		Calendar calender=Calendar.getInstance();
		
		return 556+ this.generateTicketId()+ calender.get(Calendar.YEAR);
	}

}

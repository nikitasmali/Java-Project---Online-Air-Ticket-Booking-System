package com.airline.serviceimpl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.entity.User;
import com.airline.repository.UserRepository;
import com.airline.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private static final Logger l = LoggerFactory.getLogger(User.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User login(String userName, String password) {
		l.info("User is logging in at "+new Date());
		User user = userRepository.findByUserNameAndPassword(userName, password);
		return user;
	}

}

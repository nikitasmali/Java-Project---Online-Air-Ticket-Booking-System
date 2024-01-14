package com.airline.service;

import com.airline.entity.User;

public interface UserService {
	User login(String userName, String password);
}

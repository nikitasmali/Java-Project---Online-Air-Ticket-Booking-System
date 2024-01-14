package com.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airline.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
public User findByUserNameAndPassword(String userName, String password);
}

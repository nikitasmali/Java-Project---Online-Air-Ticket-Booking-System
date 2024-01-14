package com.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airline.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

}

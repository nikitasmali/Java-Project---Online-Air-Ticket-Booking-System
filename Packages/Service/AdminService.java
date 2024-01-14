package com.airline.service;

import java.util.List;

import com.airline.dto.AdminDTO;
import com.airline.entity.Admin;


public interface AdminService {
	//String createAdmin(Admin admin);
	AdminDTO createAdmin(Admin admin);
	AdminDTO updateAdmin(int id, Admin admin);
	AdminDTO getAdminById(int id);
	List<AdminDTO> getAllAdmin();
	void deleteAdminById(int id);
	void deleteAllAdmins();
}

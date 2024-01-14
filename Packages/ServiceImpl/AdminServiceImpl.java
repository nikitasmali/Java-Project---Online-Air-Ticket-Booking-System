package com.airline.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.dto.AdminDTO;
import com.airline.entity.Admin;
import com.airline.entity.Passenger;
import com.airline.exception.ResourceNotFoundException;
import com.airline.repository.AdminRepository;
import com.airline.service.AdminService;
import com.airline.util.Converter;

@Service
public class AdminServiceImpl implements AdminService {

	//logger statistically created
	private static final Logger l = LoggerFactory.getLogger(Admin.class);
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private Converter converter;
	
//	@Override
//	public String createAdmin(Admin admin) {
//		String msg = null;
//		
//		admin.setUserName(admin.getUserName());
//		admin.setPassword(admin.getPassword());
//		admin.setRole(admin.getRole());
//		
//		adminRepository.save(admin);
//		if(admin!=null)
//		{
//			msg="Admin details saved successfully!";
//		}
//		return msg;
//	}

	@Override
	public AdminDTO createAdmin(Admin admin) {
		admin.setUserName(admin.getUserName());
		admin.setPassword(admin.getPassword());
		admin.setRole(admin.getRole());
		
		adminRepository.save(admin);
		l.info("Admin "+admin.toString()+" added at "+new Date());
		return converter.convertToAdminDTO(admin);
	}
	
	@Override
	public AdminDTO updateAdmin(int id, Admin admin) {
		Admin adminPresent = adminRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Admin", "id", id));
		
		adminPresent.setName(admin.getName());
		adminPresent.setEmail(admin.getEmail());
		adminPresent.setUserName(admin.getUserName());
		adminPresent.setPassword(admin.getPassword());
		adminPresent.setRole(admin.getRole());
		
		adminRepository.save(adminPresent);
		l.info("Admin with id "+ id +" updated at "+new Date());
		return converter.convertToAdminDTO(adminPresent);
	}

	@Override
	public AdminDTO getAdminById(int id) {
		Admin admin = adminRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Admin", "id", id));
		l.info("Getting admin details of "+id+" at "+new Date());
		return converter.convertToAdminDTO(admin);
	}

	@Override
	public List<AdminDTO> getAllAdmin() {
		List<Admin> admins = adminRepository.findAll();
		l.info("Getting all admin details at "+ new Date());
		List<AdminDTO> adminDTO = new ArrayList<>();
		for(Admin ad: admins)
		{
			adminDTO.add(converter.convertToAdminDTO(ad));
		}
		return adminDTO;
	}

	@Override
	public void deleteAdminById(int id) {
		l.info("Deleting admin details with id" +id+ " at "+new Date());
		adminRepository.deleteById(id);
		
	}

	@Override
	public void deleteAllAdmins() {
		l.info("Deleting all admin details at "+new Date());
		adminRepository.deleteAll();
	}

	

}

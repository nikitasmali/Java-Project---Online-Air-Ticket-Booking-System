package com.airline.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.dto.AdminDTO;
import com.airline.entity.Admin;
import com.airline.service.AdminService;
import com.airline.util.Converter;

@RestController
@RequestMapping("/api")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	Converter converter;
	
	//build create admin REST API
//	@PostMapping("/createAdmin")
//	public String createAdmin(@RequestBody AdminDTO adminDTO)
//	{
//		final Admin admin = converter.convertToAdminEntity(adminDTO);
//		return adminService.createAdmin(admin);
//	}
	
	//build create admin REST API
	@PostMapping("/createAdmin")
	public ResponseEntity<AdminDTO> createAdmin(@RequestBody AdminDTO adminDTO)
	{
		final Admin adm1 = converter.convertToAdminEntity(adminDTO);
		return new ResponseEntity<AdminDTO>(adminService.createAdmin(adm1),
				HttpStatus.CREATED);
	}
	
	//build update admin REST API
		//localhost:8806/updateAdmin/1
		@PutMapping("/updateAdmin/{id}")
		public ResponseEntity<AdminDTO> updateAdmin(@PathVariable("id") int id,
				@RequestBody AdminDTO adminDTO)
		{
			final Admin admin= converter.convertToAdminEntity(adminDTO);
			return new ResponseEntity<AdminDTO>(adminService.updateAdmin(id, admin),
					HttpStatus.OK);
		}
		
		
	//build GET admin REST API
	//localhost:8086/getAdminById/1
	@GetMapping("/getAdminById/{id}")	
	public AdminDTO getAdminById(@PathVariable int id)
	{
		return adminService.getAdminById(id);
	}
	
	
	//build GET ALL admin REST API
	@GetMapping("/getAllAdmin")
	public List<AdminDTO> getAllAdmin()
	{
		return adminService.getAllAdmin();
	}
	
	
	//build DELETE by id admin REST API
	@DeleteMapping("/deleteAdminById/{id}")
	public ResponseEntity<String> deleteAdminById(@PathVariable int id)
	{
//		String msg = "Admin details has been deleted";
//		adminService.deleteAdminById(id);
//		return msg;
		adminService.deleteAdminById(id);
		return new ResponseEntity<String>("Admin details have been deleted successfully!!",
				HttpStatus.OK);
	}
	
	//build DELETE ALL admin REST API
	@DeleteMapping("/deleteAllAdmins")
	public ResponseEntity<String> deleteAllAdmins()
	{
		adminService.deleteAllAdmins();
		return new ResponseEntity<String>("All admin details have been deleted successfully!!",
				HttpStatus.OK);
	}
	
	
}

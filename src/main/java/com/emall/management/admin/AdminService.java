package com.emall.management.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin")
@CrossOrigin
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@PostMapping(path = "/add")
	public Admin addAdmin(@RequestBody Admin newAdmin) {
		for(Admin admin: adminRepository.findAll()) {
			if(admin.getEmail().equals(newAdmin.getEmail())) {
				return null;
			} 
		}
		adminRepository.save(newAdmin);
		return newAdmin;
	}
	
	@PutMapping(path = "/update")
	public Admin updateAdmin(@RequestBody Admin newAdmin) {
		return adminRepository.findById(newAdmin.getAdminId()).map(admin -> {
			admin.setEmail(newAdmin.getEmail());
			admin.setUsername(newAdmin.getUsername());
			admin.setPassword(newAdmin.getPassword());
			admin.setMall(newAdmin.getMall());
			return adminRepository.save(admin);
		}).get();
	}
	
	@GetMapping(path = "/get")
	public Admin getAdmin(@RequestParam Integer id) {
		return adminRepository.findById(id).get();
	}
	
	@GetMapping(path = "/all")
	public Iterable<Admin> getAll() {
		return adminRepository.findAll();
	}
	
	@GetMapping(path = "/msg")
	public String hello() {
		return "hello from admin !!";
	}
	
}

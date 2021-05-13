package com.emall.management.admin;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

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
	public String addAdmin(@RequestBody Admin newAdmin) throws Exception {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest
				.newBuilder()
				.uri(URI.create("http://localhost:8082/staff/is-staff?email=" + newAdmin.getEmail()))
				.GET()
				.build();
		
		if(client.send(request, BodyHandlers.ofString()).body().equals("true")) return null;
		for(Admin admin: adminRepository.findAll()) {
			if(admin.getEmail().equals(newAdmin.getEmail())) {
				return null;
			} 
		}
		adminRepository.save(newAdmin);
		return "Registered successfully !";
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
	
	@PostMapping(path = "/login")
	public Admin login(@RequestBody Admin user) {
		for(Admin admin: adminRepository.findAll()) {
			if(admin.getEmail().equals(user.getEmail()) &&
			   admin.getPassword().equals(user.getPassword())) {
				return admin;
			}
		}
		return null;
	}
	
	@GetMapping(path = "/is-admin")
	public String isAdmin(@RequestBody String email) {
		return adminRepository.existsByEmail(email) ? "true": "false";
	}
	
}

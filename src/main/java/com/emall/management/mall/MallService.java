package com.emall.management.mall;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emall.management.admin.AdminRepository;

@RestController
@RequestMapping(path = "/mall")
@CrossOrigin
public class MallService {

	@Autowired
	private MallRepository mallRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	
	@PostMapping(path = "/add")
	public Mall addMall(@RequestBody Mall mall, @RequestParam Integer id) {
		return adminRepository.findById(id).map(admin -> {
			mall.setAdmin(admin);
			admin.setMall(mallRepository.save(mall));
			adminRepository.save(admin);
			return mall;
		}).get();
	}
	
	@GetMapping(path = "/get")
	public Mall getMall(@RequestParam Integer id) {
		return mallRepository.findById(id).get();
	}
	
	@GetMapping(path = "/all")
	public Iterable<Mall> getMalls() {
		return mallRepository.findAll();
	}
	
	@GetMapping(path = "/space-cost")
	public String getSpaceCost(@RequestParam Integer id) {
		return mallRepository.findById(id).get().getSpaceCost();
	}
	
	@DeleteMapping(path = "/delete")
	public String deleteMall(@RequestParam Integer id) throws Exception {
		mallRepository.deleteById(id);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest
				.newBuilder()
				.DELETE()
				.uri(URI.create("http://localhost:8082/staff/delete-by-mall?id="+id))
				.build();
		if(client.send(request, BodyHandlers.ofString()).body().equals("success"))
		return "deleted successfully !";
		else return null;
	}
}

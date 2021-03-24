package com.emall.management.mall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@GetMapping(path = "/all")
	public Iterable<Mall> getMalls() {
		return mallRepository.findAll();
	}
	
	@GetMapping(path = "/space-cost")
	public String getSpaceCost(@RequestParam Integer id) {
		return mallRepository.findById(id).get().getSpaceCost();
	}
}

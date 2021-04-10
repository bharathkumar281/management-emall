package com.emall.management.floor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emall.management.mall.MallRepository;

@RestController
@RequestMapping(path = "/floor")
@CrossOrigin
public class FloorService {

	@Autowired
	private FloorRepository floorRepository;
	
	@Autowired
	private MallRepository mallRepository;
	
	@PostMapping(path = "/add")
	public Floor addFloor(@RequestParam Integer id) {
		return mallRepository.findById(id).map(mall -> {
			Floor floor = new Floor();
			floor.setMall(mall);
			mall.getFloors().add(floorRepository.save(floor));
			mallRepository.save(mall);
			return floor;
		}).get();
	}
	
	@GetMapping(path = "/all")
	public Iterable<Floor> getFloors() {
		return floorRepository.findAll();
	}
}

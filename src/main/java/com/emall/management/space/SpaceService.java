package com.emall.management.space;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emall.management.floor.FloorRepository;

@RestController
@RequestMapping(path = "/space")
public class SpaceService {
	
	@Autowired
	private SpaceRepository spaceRepository;
	
	@Autowired
	private FloorRepository floorRepository;
	
	@PostMapping(path = "/add")
	public Space addSpace(@RequestParam Integer id) {
		return floorRepository.findById(id).map(floor -> {
			Space space = new Space();
			space.setFloor(floor);
			floor.getSpaces().add(spaceRepository.save(space));
			floorRepository.save(floor);
			return space;
		}).get();
	}
	
	@GetMapping(path = "/all")
	public Iterable<Space> getSpaces() {
		return spaceRepository.findAll();
	}
}

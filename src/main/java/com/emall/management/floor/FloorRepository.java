package com.emall.management.floor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.emall.management.floor.Floor;

@Repository
public interface FloorRepository extends CrudRepository<Floor, Integer> {

}

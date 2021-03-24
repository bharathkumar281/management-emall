package com.emall.management.space;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emall.management.space.Space;

@Repository
public interface SpaceRepository extends CrudRepository<Space, Integer> {

}

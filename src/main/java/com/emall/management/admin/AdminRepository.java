package com.emall.management.admin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emall.management.admin.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {

}

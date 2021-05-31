package com.cg.policy.Insurance.Policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.policy.Insurance.Policy.model.Admin;


@Repository
public interface AdminRepository  extends JpaRepository<Admin,Integer>{

	//Admin findByMobileAndPassword(String mobile, String password);

	Admin findByEmail(String email);
	

}

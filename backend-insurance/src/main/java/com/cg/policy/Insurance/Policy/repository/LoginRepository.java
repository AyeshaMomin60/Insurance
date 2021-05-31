package com.cg.policy.Insurance.Policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.policy.Insurance.Policy.model.Login;

@Repository
public interface LoginRepository  extends JpaRepository<Login,Integer> {

	//Login findByMobileAndPassword(String mobile, String password);

}

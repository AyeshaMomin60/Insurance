package com.cg.policy.Insurance.Policy.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.policy.Insurance.Policy.model.Admin;
import com.cg.policy.Insurance.Policy.model.Login;
import com.cg.policy.Insurance.Policy.model.Plan;
import com.cg.policy.Insurance.Policy.model.User;
import com.cg.policy.Insurance.Policy.repository.AdminRepository;
import com.cg.policy.Insurance.Policy.repository.LoginRepository;
import com.cg.policy.Insurance.Policy.repository.PlanRepository;

@Service
@Transactional
public class AdminService {
	
	@Autowired
	AdminRepository Repository;
	
	@Autowired
	LoginRepository loginRepository;
	
	@Autowired
	PlanRepository planRepository;

	public Admin addAdmin(Admin admin) {
		return Repository.save(admin);
	}

	public Login addLogin(Login login) {
		return loginRepository.save(login);
	}

	public Admin getLoginRecord(String email, String password) {
		return Repository.findByEmail(email);
	}

	public Plan viewPlanByPlanId(int planId) {
		return planRepository.findByPlanId(planId);
	}

	public Plan viewPlanByName(String name) {
		return planRepository.findPlanByName(name);
	}

	

	


	
	
}

package com.cg.policy.Insurance.Policy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cg.policy.Insurance.Policy.model.EnrollmentRequest;
import com.cg.policy.Insurance.Policy.model.Plan;
import com.cg.policy.Insurance.Policy.model.User;
import com.cg.policy.Insurance.Policy.repository.EnrollmentRepository;
import com.cg.policy.Insurance.Policy.repository.PlanRepository;

@Service
@Transactional
public class PlanService {

	@Autowired
	PlanRepository repository;
	
	

	@Autowired
	EnrollmentRepository enrollmentrepository;

	public Plan addPlan(Plan plan) {
		return repository.save(plan);
	}


	public Plan UpdatePlan(Plan plan) {	
		return repository.save(plan);
	}

	public Plan getPlan(int planId) {
		
		return repository.findByPlanId(planId);
	}

	public List<Plan> getAllPlan() {
		return repository.findAll();
	}

	public void deletePlan(Plan plan) {
		repository.delete(plan);
	}

	public Plan findByPlaneId(int planId) {
		return repository.findByPlanId(planId);
	}


	public List<Plan> showPlan() {
		return repository.findAll();
	}


//	public boolean deletePlanById(Plan plan) {
//		return repository.deletePlan(plan);
//	}


	public Plan deletePlans(int planId) {
		Plan user = findByPlaneId(planId);
        if(user != null){
            repository.delete(user);
        }
        return user;
	}


	


	public Plan getPlanByPlanId(int planId) {
		return repository.findByPlanId(planId);
	}


	

//	public Object deletePlanById(int planId) {
//		return repository.deleteById(planId);
//	}

	


	

	
	
}

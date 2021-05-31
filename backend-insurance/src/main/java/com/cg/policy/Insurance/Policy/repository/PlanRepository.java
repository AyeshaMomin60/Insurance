package com.cg.policy.Insurance.Policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.policy.Insurance.Policy.model.EnrollmentRequest;
import com.cg.policy.Insurance.Policy.model.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan,Integer> {

	Plan findByPlanId(int planId);

	Plan findPlanByName(String name);

	//boolean deletePlan(Plan plan);

	

	

	
}

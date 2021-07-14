package com.cg.policy.Insurance.Policy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.policy.Insurance.Policy.Util.JwtUtil;
import com.cg.policy.Insurance.Policy.exception.InsuranceException;
import com.cg.policy.Insurance.Policy.model.Policy;
import com.cg.policy.Insurance.Policy.model.User;
import com.cg.policy.Insurance.Policy.repository.EnrollmentRepository;
import com.cg.policy.Insurance.Policy.repository.PlanRepository;

import javassist.NotFoundException;

@Service

public class PlanService {

	@Autowired
	UserService userService;

	@Autowired
	PlanRepository planRepository;

	@Autowired
	EnrollmentRepository enrollmentRepository;

	Logger logger = LoggerFactory.getLogger(PlanService.class);
	
	/**
	 * This method return {@link Policy}
	 * 
	 * @param policy
	 * @return {@link Policy}
	 */
	@Transactional
	public Policy addPlan(String token,Policy policy) {
		int verifiedId = JwtUtil.tokenVerification(token);
		policy.setDeleted(true);
		return planRepository.save(policy);
	}

	/**
	 * This method return {@link Policy}
	 * 
	 * @param policy
	 * @return {@link Policy}
	 * @throws InsuranceException
	 */
	@Transactional
	public ResponseEntity<Policy> updatePlan(String token,Policy policy) throws InsuranceException {
		int verifiedId = JwtUtil.tokenVerification(token);
		Policy planDetails = getPlan(policy.getPlanId());
		if (planDetails == null) {
			throw new InsuranceException("no plan present");
		}
		planDetails.setCost(policy.getCost());
		planDetails.setDetuctableAmount(policy.getDetuctableAmount());
		planRepository.save(planDetails);
		return new ResponseEntity<>(planDetails, HttpStatus.OK);
	}

	/**
	 * This method return {@link Policy}
	 * 
	 * @param policyId
	 * @return {@link Policy}
	 */
	public Policy getPlan(int planId) {
         
		return planRepository.findByPlanId(planId);
	}

	/**
	 * This method return {@link Policy}
	 * 
	 * @return {@link Policy}
	 * @throws NotFoundException 
	 */
	public List<Policy> getAllPlan(String token) throws NotFoundException {
		int verifiedId = JwtUtil.tokenVerification(token);
		List<Policy> policies = planRepository.findAll();
        if(policies.isEmpty()) {
            throw new NotFoundException("Policy list is empty");
        }
        
        logger.info("All  policies returned from Policy Service");
        return policies;
	}

	/**
	 * This method return {@link Policy}
	 * 
	 * @param policyId
	 * @return {@link Policy}
	 */
	public Policy findByPlaneId(int planId) {
		return planRepository.findByPlanId(planId);
	}

	/**
	 * This method return {@link Policy}
	 * 
	 * @return {@link Policy}
	 */
	public List<Policy> showPlan(String token) {
		int verifiedId = JwtUtil.tokenVerification(token);
		return planRepository.findAll();
	}

	/**
	 * This method return {@link Policy}
	 * @param token 
	 * 
	 * @param policy Name
	 * @return {@link Policy}
	 */
	@Transactional
	public Policy deletePlans(String token, int planId) {
		int verifiedId = JwtUtil.tokenVerification(token);
		Policy plan = findByPlaneId(planId);
		plan.setDeleted(false);
		return planRepository.save(plan);
	}

	/**
	 * This method return {@link Policy}
	 * 
	 * @param policyId
	 * @return {@link Policy}
	 * @throws InsuranceException
	 */

	public ResponseEntity<Policy> getPlanByPlanId(String token,int userId) throws InsuranceException {
		int verifiedId = JwtUtil.tokenVerification(token);
		User userDetails = userService.getEnrollmentDetails(token,userId);
		if (userDetails == null) {
			throw new InsuranceException("No Policy Is being Taken");
		}
		Policy policy = planRepository.findByPlanId(userDetails.getPolicy().getPlanId());
		return new ResponseEntity<>(policy, HttpStatus.OK);

	}

	public Policy findPlanByPlaneId(int planId) {
		return planRepository.findByPlanId(planId);
	}

	
}

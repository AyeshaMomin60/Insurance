package com.cg.policy.Insurance.Policy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.policy.Insurance.Policy.Util.JwtUtil;
import com.cg.policy.Insurance.Policy.exception.InsuranceException;
import com.cg.policy.Insurance.Policy.model.Policy;
import com.cg.policy.Insurance.Policy.repository.AdminRepository;
import com.cg.policy.Insurance.Policy.repository.PlanRepository;

@Service
public class AdminService {

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	PlanRepository planRepository;

	Logger logger = LoggerFactory.getLogger(AdminService.class);

	

	
	
	
	/**
	 * This method return {@link Policy}
	 * @param token 
	 * 
	 * @param policyId
	 * @return {@link Policy}
	 * @throws InsuranceException
	 */
	public Policy viewPlanByPlanId(String token, int planId) throws InsuranceException {
		int verifiedId = JwtUtil.tokenVerification(token);
		logger.info("Policy Id is :" + planId);
		Policy policy = planRepository.findByPlanId(planId);
		logger.info("Policy with given id is :" + policy);
		if (policy == null) {
			logger.info("no user present");
			throw new InsuranceException("NO Policy present with given id:" + planId);
		}
		return policy;

	}

	/**
	 * This method return {@link Policy}
	 * @param token 
	 * 
	 * @param policy Name
	 * @return {@link Policy}
	 * @throws InsuranceException
	 */
	public Policy viewPlanByName(String token, String name) throws InsuranceException {
		int verifiedId = JwtUtil.tokenVerification(token);
		Policy policy = planRepository.findPlanByName(name);
		if (policy == null) {
			logger.info("No policy present");
			throw new InsuranceException("NOT FOUND");
		} else {
			return policy;
		}

	}

}

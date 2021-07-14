package com.cg.policy.Insurance.Policy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cg.policy.Insurance.Policy.exception.InsuranceException;
import com.cg.policy.Insurance.Policy.model.Policy;
import com.cg.policy.Insurance.Policy.model.User;
import com.cg.policy.Insurance.Policy.service.AdminService;
import com.cg.policy.Insurance.Policy.service.PlanService;
import com.cg.policy.Insurance.Policy.service.UserService;

import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

/**
 * @author aymomin
 *
 */
@RestController
@RequestMapping(path = "api")
@CrossOrigin

public class AdminController {

	@Autowired
	AdminService service;

	@Autowired
	PlanService planService;

	@Autowired
	UserService userService;

	Logger logger = LoggerFactory.getLogger(AdminController.class);

	/**
	 * This method return {@link Policy} that is added
	 * 
	 * @param policy
	 * @return {@link Policy}
	 */

	@PostMapping("/createPlan")
	public ResponseEntity<Policy> addPlan(@RequestBody Policy policy, HttpServletRequest request) {
		String token = request.getHeader("token");
		Policy result = planService.addPlan(token, policy);
		logger.info("New plan added successfully");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	/**
	 * This method return {@link Policy} that is updated
	 * 
	 * @param policy
	 * @return {@link Policy}
	 */
	@PutMapping("/updatePlan")
	public ResponseEntity<Policy> updatePlan(@RequestBody Policy policy, HttpServletRequest request)
			throws InsuranceException {
		String token = request.getHeader("token");
		return planService.updatePlan(token, policy);

	}

	/**
	 * This method Delete the Policy {@link Policy}
	 * 
	 * @param policyId
	 * @return {@link Policy}
	 */
	@PutMapping(path = { "/deletePlan/{planId}" })
	public Policy delete(@PathVariable("planId") int planId, HttpServletRequest request) {
		String token = request.getHeader("token");
		return planService.deletePlans(token, planId);
	}

	/**
	 * This method return {@link Policy}
	 * 
	 * @param policyId
	 * @return {@link Policy}
	 * @throws InsuranceException
	 */
	@GetMapping("/viewPlanById/{planId}")
	public Policy viewUserbyEmail(@PathVariable("planId") int planId, HttpServletRequest request)
			throws InsuranceException {
		String token = request.getHeader("token");
		return service.viewPlanByPlanId(token, planId);

	}

	/**
	 * This method return {@link Policy}
	 * 
	 * @param policy Name
	 * @return {@link Policy}
	 * @throws InsuranceException
	 */

	@GetMapping("/viewPlanByName/{Name}")
	public Policy viewUserbyEmail(@RequestParam("name") String name, HttpServletRequest request)
			throws InsuranceException {
		String token = request.getHeader("token");
		logger.info("Plans by name" + name + " is" + service.viewPlanByName(token, name));
		return service.viewPlanByName(name, token);

	}

	/**
	 * This method return {@link List} of {@link Policy}
	 * 
	 * @return {@link List} of {@link Policy}
	 * @throws NotFoundException
	 */
	@ApiOperation(value = "Show All Plan", response = Policy.class)
	@GetMapping("/allPlans")
	public ResponseEntity<List<Policy>> allPlans(HttpServletRequest request) throws NotFoundException {
		String token = request.getHeader("token");
		List<Policy> list = planService.getAllPlan(token);
		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	/**
	 * This method return {@link User}
	 * 
	 * @param policyId
	 * @return {@link User}
	 */
	@ApiOperation(value = "Show All User Based On Plan", response = Policy.class)
	@GetMapping("/showPlanTakenByUser/{planId}")
	public ResponseEntity<List<User>> showPlanTakenByUser(@PathVariable("planId") int planId,
			HttpServletRequest request) throws InsuranceException {
		String token = request.getHeader("token");
		return userService.findUserByPolicy(token, planId);

	}

}

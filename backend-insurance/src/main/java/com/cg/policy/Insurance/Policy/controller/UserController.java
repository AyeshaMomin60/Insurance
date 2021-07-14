package com.cg.policy.Insurance.Policy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.security.UserDetails;
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
import org.springframework.web.bind.annotation.RestController;

import com.cg.policy.Insurance.Policy.exception.InsuranceException;
import com.cg.policy.Insurance.Policy.model.EnrollmentRequest;
import com.cg.policy.Insurance.Policy.model.Policy;
import com.cg.policy.Insurance.Policy.model.User;
import com.cg.policy.Insurance.Policy.service.PlanService;
import com.cg.policy.Insurance.Policy.service.UserService;

import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

/**
 * @author aymomin
 *
 */
@RestController
@RequestMapping(path = "/api")
@CrossOrigin
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	PlanService planService;

	/**
	 * 
	 * @param user
	 * @return This method return {@link User} with their respective Details
	 * @throws InsuranceException
	 */
	@PostMapping("/addUser")

	public ResponseEntity<User> registerUser(@RequestBody User user) throws InsuranceException {
		return userService.addUser(user);
	}

	/**
	 * This method return {@link User}
	 * 
	 * @param Email and Password
	 * @return {@link User}
	 * @throws InsuranceException 
	 */
	@PostMapping(value = "/login")
	public ResponseEntity<?> userLogin(@RequestBody User loginRequest, HttpServletRequest request,
			HttpServletResponse response) throws InsuranceException {
		User userDetails = userService.getLoginRecord(loginRequest.getEmail(), loginRequest.getPassword());
		String GeneratedToken = userService.userLogin(loginRequest, response);
		if (GeneratedToken != null) {
			if (userDetails != null && ((userDetails.getRole().equals("User") ||userDetails.getRole().equals("Admin")
					|| userDetails.getRole().equals("SUPER-ADMIN")) && userDetails.getStatus().equalsIgnoreCase("Active"))) {
			return new ResponseEntity<>(userDetails,HttpStatus.OK);
			}
			else {
				throw new InsuranceException("No User Present"); 
			}
			
		} 
		else
			return new ResponseEntity<>("{Invalid user Details}", HttpStatus.BAD_REQUEST);
		
	}

//	/**
//	 * This method return {@link User}
//	 * 
//	 * @param Email and Password
//	 * @return {@link User}
//	 */
//
//	@ApiOperation(value = "get login record by username and password", response = User.class)
//	@GetMapping("/user/login/{email}/{password}")
//
//	public ResponseEntity<User> getLogin(@PathVariable("email") String email, @PathVariable("password") String password)
//			throws InsuranceException {
//		User userDetails = userService.getLoginRecord(email, password);
//		if (userDetails != null && ((userDetails.getRole().equals("User") ||userDetails.getRole().equals("Admin")
//				|| userDetails.getRole().equals("SUPER-ADMIN")) && userDetails.getStatus().equalsIgnoreCase("Active"))) {
//
//			return new ResponseEntity<>(userDetails, HttpStatus.OK);
//
//		} else {
//			logger.info("No User Present");
//			throw new InsuranceException("No User Present");
//
//		}
//
//	}

	/**
	 * This method return {@link Policy}
	 * 
	 * @return {@link Policy}
	 * @throws NotFoundException
	 */

	@ApiOperation(value = "View Plans", response = Policy.class)
	@GetMapping("/showAllPlans")
	public ResponseEntity<List<Policy>> showAllPlans(HttpServletRequest request) throws NotFoundException {

		String token = request.getHeader("token");
		List<Policy> list = planService.getAllPlan(token);
		logger.info("List Of Plans" + list);
		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	/**
	 * This method return {@link Policy}
	 * 
	 * @param userId
	 * @return {@link Policy}
	 */

	@ApiOperation(value = "get policy taken by user", response = User.class)
	@GetMapping("/userTakenPlan/{userId}")
	public ResponseEntity<Policy> ViewEnrollPlan(@PathVariable("userId") int userId, HttpServletRequest request)
			throws InsuranceException {
		String token = request.getHeader("token");
		return planService.getPlanByPlanId(token, userId);

	}

	/**
	 * This method return {@link List} of {@link User} with their respective
	 * {@link Policy}
	 * 
	 * @param id : policyId
	 * @return {@link List} of {@link User}
	 */
	@ApiOperation(value = "get user By user Id", response = User.class)
	@GetMapping("/userByUserId/{userId}")

	public User UserDetailsBuUserId(@PathVariable("userId") int userId,HttpServletRequest request) throws InsuranceException {
		String token = request.getHeader("token");
		return userService.getEnrollmentDetails(token,userId);
	}

	/**
	 * This method return {@link EnrollmentRequest}
	 * 
	 * @param Enrollment Details
	 * @return {@link EnrollmentRequest }
	 * @throws InsuranceException
	 */
	@PostMapping("/enrollIntoPlane")
	@ApiOperation("Enrolls a member in a benefit plan")
	public ResponseEntity<EnrollmentRequest> createEnrollment(@RequestBody EnrollmentRequest enrollRequest,HttpServletRequest request)
			throws InsuranceException {
		String token = request.getHeader("token");
		return userService.addEnrollmentDetails(token,enrollRequest);
	}

	/**
	 * This method cancel the enrollment
	 * 
	 * @param EnrollmentId
	 * 
	 * 
	 */
	@GetMapping("/userDetails/{email:.+}/")
	// @GetMapping("/userDetails/{email:.+}")
	public User UserDetails(@PathVariable(value = "email") String email,HttpServletRequest request) {
		String token = request.getHeader("token");
		User user = userService.findUserByEmail(token,email);
		return user;
	}

	/**
	 * This method return {@link ListofUser}
	 * 
	 * @return {@link ListofUser }
	 * @throws NotFoundException
	 * 
	 */

	@ApiOperation(value = "List of User", response = Policy.class)
	@GetMapping("/listOfUser")
	public ResponseEntity<List<User>> showAllUser(HttpServletRequest request) throws NotFoundException {
		String token = request.getHeader("token");
		return userService.listOfUser(token);

	}

	/**
	 * This method update {@link UserDetails}
	 * 
	 * @param user
	 * @return {@link UserDetails }
	 * 
	 */

	@PutMapping("/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody User user,HttpServletRequest request) throws InsuranceException {
		String token = request.getHeader("token");
		logger.info("User Details Updated Successfully" + userService.updateUser(token,user));
		return userService.updateUser(token,user);
	}

}
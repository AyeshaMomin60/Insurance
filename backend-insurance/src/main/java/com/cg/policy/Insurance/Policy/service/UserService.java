package com.cg.policy.Insurance.Policy.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.policy.Insurance.Policy.Util.Encryption;
import com.cg.policy.Insurance.Policy.Util.JwtUtil;
import com.cg.policy.Insurance.Policy.exception.InsuranceException;
import com.cg.policy.Insurance.Policy.model.EnrollmentRequest;
import com.cg.policy.Insurance.Policy.model.Policy;
import com.cg.policy.Insurance.Policy.model.User;
import com.cg.policy.Insurance.Policy.repository.EnrollmentRepository;
import com.cg.policy.Insurance.Policy.repository.UserRepository;

import javassist.NotFoundException;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	EnrollmentRepository enrollmentRepository;

	@Autowired
	PlanService planService;

	private static final String SUPERADMIN = "SUPERADMIN";

	private static final String USER_STATUS = "INACTIVE";

	private static final String USER_ROLE = "USER";

	Logger logger = LoggerFactory.getLogger(UserService.class);

	/**
	 * This method return {@link User}
	 * 
	 * @param User
	 * @return {@link User}
	 */
	@Transactional
	public ResponseEntity<User> addUser(User user) throws InsuranceException {
		String email = user.getEmail();

		if (email != null && !"".equals(email)) {
			User userDetails = fetchUserByEmail(email);
			if (userDetails != null) {
				logger.info("User Already Exsist");
				throw new InsuranceException("User Already Exsist");
			}

		}
		user.setRole(USER_ROLE);
		user.setStatus(USER_STATUS);
		user.setPassword(Encryption.encryptedPassword(user.getPassword()));
		User users = userRepository.save(user);

		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	/**
	 * This method return {@link User}
	 * 
	 * @param User
	 * @return {@link User}
	 */
	public User getLoginRecord(String email, String password) {
		User user = userRepository.findByEmail(email);
		logger.info("User Details" + user);
		return user;
	}

	/**
	 * This method return {@link request}
	 * @param token 
	 * 
	 * @param Enrollment
	 * @return {@link User}
	 * @throws InsuranceException
	 */
	@Transactional
	public ResponseEntity<EnrollmentRequest> addEnrollmentDetails(String token, EnrollmentRequest request) throws InsuranceException {
		int verifiedId = JwtUtil.tokenVerification(token);
		User users = findByUserId(request.getUser().getUserId());
		logger.info("users" + users);
		Policy policy = planService.findPlanByPlaneId(request.getPolicy().getPlanId());
		logger.info("Policy" + policy);
		if (policy == null) {
			logger.info("No policy present");
			throw new InsuranceException("No policy present");
		}
		if (users == null) {
			logger.info("No user present");
			throw new InsuranceException("No User present");
		}

		setPlanId(users, policy);
		EnrollmentRequest enrollmentDetails = enrollmentRepository.save(request);
		logger.info("Enrollment Details:" + enrollmentDetails);
		return new ResponseEntity<>(enrollmentDetails, HttpStatus.OK);

	}

	/**
	 * This method return {@link User,plan}
	 * 
	 * @param User
	 * @return {@link User}
	 */
	public void setPlanId(User users, Policy policy) {
		users.setPolicy(policy);
		userRepository.save(users);
	}

	/**
	 * This method return {@link User}
	 * 
	 * @param userId
	 * @return {@link User}
	 */
	public User findByUserId(int userId) {
		return userRepository.findByUserId(userId);
	}

	/**
	 * This method return {@link Enrollment}
	 * 
	 * @param EnrollmentId
	 * @return {@link Enrollment}
	 */

	public EnrollmentRequest findByEnrollmentId(int enrollmentId) {

		return enrollmentRepository.findByEnrollmentId(enrollmentId);
	}

	/**
	 * This method return {@link String}
	 * 
	 * @param EnrollmentId
	 * @return {@link String}
	 */
	@Transactional
	public String deleteByEnrollmentId(int enrollmentId) {

		enrollmentRepository.deleteById(enrollmentId);
		return ("Request deleted successfully");
	}

	/**
	 * This method return {@link User}
	 * 
	 * @param Email
	 * @return {@link User}
	 */

	public User fetchUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	/**
	 * This method return {@link User}
	 * 
	 * @param Email,Password
	 * @return {@link User}
	 */
	public User fetchUserByEmailandPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	/**
	 * This method return {@link User}
	 * 
	 * @param Email
	 * @return {@link User}
	 */
	public User viewUserbyEmail(String email) {

		return userRepository.findByEmail(email);
	}

	/**
	 * This method return {@link User}
	 * 
	 * @param token
	 * 
	 * @param UserId
	 * @return {@link User}
	 * @throws InsuranceException
	 */
	public User getEnrollmentDetails(String token, int userId) throws InsuranceException {
		int verifiedId = JwtUtil.tokenVerification(token);
		User user = userRepository.findByUserId(userId);
		if (user == null) {
			logger.info("No user present");
			throw new InsuranceException("No Policy Is being Taken");
		}
		logger.info("Display All User Enrollment Details based on Id" + userId);
		return user;

	}

	/**
	 * This method return {@link User}
	 * 
	 * @param email2
	 * 
	 * @param Email
	 * @return {@link User}
	 */
	public User findUserByEmail(String token, String email) {
		int verifiedId = JwtUtil.tokenVerification(token);
		return userRepository.findByEmail(email);
	}

	/**
	 * This method return {@link User}
	 * 
	 * @param token
	 * 
	 * @return {@link User}
	 * @throws NotFoundException
	 */
	public ResponseEntity<List<User>> listOfUser(String token) throws NotFoundException {
		int verifiedId = JwtUtil.tokenVerification(token);
		List<User> allUsers = userRepository.findAll();
		if (allUsers.isEmpty()) {
			throw new NotFoundException("User list is empty");
		}
		logger.info("All Users returned from User Service");
		return new ResponseEntity<>(allUsers, HttpStatus.OK);

	}

	/**
	 * This method return {@link User}
	 * 
	 * @param UserId
	 * @return {@link User}
	 */
	public User getUser(int userId) {
		return userRepository.findByUserId(userId);
	}

	/**
	 * This method return {@link User}
	 * 
	 * @param token
	 * 
	 * @param UserDetails
	 * @return {@link User}
	 * @throws InsuranceException
	 */
	@Transactional
	public ResponseEntity<User> updateUser(String token, User user) throws InsuranceException {
		int verifiedId = JwtUtil.tokenVerification(token);
		logger.info("Input User Details"+user);
		User userDetails = getUser(user.getUserId());
		logger.info("Fetch User Details"+userDetails);
		if (userDetails == null) {
			logger.info("No user present");
			throw new InsuranceException("no User present");
		}
//		if (userDetails.getRole().equalsIgnoreCase("SUPER-ADMIN")) {
//			logger.info("Admin Data cannot be updated");
//			throw new InsuranceException("Admin data cannot be updated");
//
//		}
		logger.info("User Role is"+user.getRole());
		logger.info("User Status is"+user.getStatus());
		userDetails.setStatus(user.getStatus());
		userDetails.setRole(user.getRole());
		userRepository.save(userDetails);
		logger.info("Display All User Details");
		return new ResponseEntity<>(userDetails, HttpStatus.OK);
	}

	/**
	 * This method return {@link User}
	 * 
	 * @return {@link User}
	 */

	public List<User> showAllUser() {

		return userRepository.findAll();

	}

	/**
	 * This method return {@link User}
	 * 
	 * @param token
	 * 
	 * @param planId
	 * @return {@link User}
	 */
	public ResponseEntity<List<User>> findUserByPolicy(String token, int planId) throws InsuranceException {

		int verifiedId = JwtUtil.tokenVerification(token);
		Policy planDetail = planService.findByPlaneId(planId);
		if (planDetail == null) {
			logger.info("No plan present");
			throw new InsuranceException("No Plan Present with given details");
		}
		List<User> userDetails = userRepository.findAllUserByPolicy(planDetail);
		if (userDetails == null) {
			logger.info("No policy taken by the user");
			throw new InsuranceException("No User Taken this Policy");
		}
		logger.info("Display all user details based on id");
		return new ResponseEntity<>(userDetails, HttpStatus.OK);

	}

	public User fetch(User users) throws NotFoundException {
		String email = users.getEmail();
		String password = users.getPassword();
		User user = null;
		if (email != null && password != null) {
			user = userRepository.findByEmailAndPassword(email, password);
		}
		if (user == null) {
			logger.info("Bad Credential");
			throw new NotFoundException("Bad Credentials");
		}
		logger.info("Display All User Details based on Email" + user);
		return user;

	}

	public String userLogin(User loginRequest, HttpServletResponse response) {
		Optional<User> maybeUser = Optional
				.of(userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword()));
		logger.info("Fetch Login Details :" + loginRequest);
		logger.info("May be User Details:" + maybeUser);
		System.out.println(maybeUser);

		if (maybeUser.isPresent()) {
			System.out.println("Successfull login");
			logger.info("Login Successfull");

			String token = JwtUtil.jwtTokenGenerator(Encryption.encryptedPassword(loginRequest.getPassword()),
					maybeUser.get().getUserId());
			if (token != null) {
				response.addHeader("token", token);
				response.addHeader("Access-control-Allow-Headers", "*");
				response.addHeader("Access-control-Expose-Headers", "*");
				return token;
			} else {
				return null;
			}
		} else
			return "Invalid User";
	}

}

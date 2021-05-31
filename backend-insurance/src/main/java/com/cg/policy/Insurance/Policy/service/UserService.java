package com.cg.policy.Insurance.Policy.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.cg.policy.Insurance.Policy.model.EnrollmentRequest;
import com.cg.policy.Insurance.Policy.model.Login;
import com.cg.policy.Insurance.Policy.model.Plan;
import com.cg.policy.Insurance.Policy.model.User;
import com.cg.policy.Insurance.Policy.repository.EnrollmentRepository;
import com.cg.policy.Insurance.Policy.repository.LoginRepository;
import com.cg.policy.Insurance.Policy.repository.UserRepository;

import io.swagger.annotations.ApiOperation;


@Transactional
@Service
public class UserService {
	@Autowired
	UserRepository Repository;
	
	@Autowired
	LoginRepository loginRepository;

	@Autowired
	EnrollmentRepository enrollmentRepository;

	public User addUser(User user) {
		return Repository.save(user);
	}

	public Login addLogin(Login login) {
		return loginRepository.save(login);
	}

	public User getLoginRecord(String email, String password) {
		 User result=Repository.findByEmail(email);
	       System.out.println(result);
	        return result;
	}

	public EnrollmentRequest addEnrollmentDetails(EnrollmentRequest request) {
		return enrollmentRepository.save(request);
		
	}

	public void setPlanId(User users, Plan result) {
		users.setPlan(result);
		Repository.save(users);
	}

	public User findByUserId(int userId) {
		return Repository.findByUserId(userId);
	}

	

//	public EnrollmentRequest DeleteByEnrollmentId(int enrollmentId) {
//		return enrollmentRepository.deleteByEnrollmentId(enrollmentId);
//	}

	public EnrollmentRequest findByEnrollmentId(int enrollmentId) {
		
		return enrollmentRepository.findByEnrollmentId(enrollmentId);
	}

	public String DeleteByEnrollmentId(int enrollmentId) {
		
		 enrollmentRepository.deleteById(enrollmentId) ;
		 return ("Request deleted successfully");
	}

	
//	public User getUserByMobile(String mobile) {
//		return Repository.findByMobile(mobile);
//	}

	public User fetchUserByEmail(String email) {
		return Repository.findByEmail(email);
	}

	public User fetchUserByEmailandPassword(String email,String password) {
		User s=Repository.findByEmailAndPassword(email,password);
		System.out.println(s);
		return Repository.findByEmailAndPassword(email,password);
	}

	public User viewUserbyEmail(String email) {
		System.out.println( "service"+Repository.findByEmail(email));
		 //email="ayesha@gmail.com";
		 return Repository.findByEmail(email);
	}

	public User getEnrollmentDetails(int userId) {
		return Repository.findByUserId(userId);
	}

	public User viewUserby(String email) {
		//email="ayesha@gmail.com";
		return Repository.findByEmail(email);
	}

	public User findUserByEmail(String userEmail) {
		System.out.println("service user email="+userEmail);
		//userEmail="ayesha@gmail.com";
		return Repository.getUserByEmail(userEmail.toString());
	}

	

	

	

	
	
}

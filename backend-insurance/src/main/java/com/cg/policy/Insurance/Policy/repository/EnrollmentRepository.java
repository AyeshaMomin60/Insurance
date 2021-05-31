package com.cg.policy.Insurance.Policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.policy.Insurance.Policy.model.EnrollmentRequest;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentRequest,Integer> {

	EnrollmentRequest findByEnrollmentId(int enrollmentId);

	EnrollmentRequest findByUser(int userId);

	

	//EnrollmentRequest save(int userId, int planId);

	
}

package com.cg.policy.Insurance.Policy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.policy.Insurance.Policy.model.Policy;

/**
 * @author Aymomin This interface extends {@link JpaRepository} which provides
 *         JPA functionalities for the entity class {@link Policy} that is being
 *         managed.
 */
@Repository
public interface PlanRepository extends JpaRepository<Policy, Integer> {

	Policy findByPlanId(int planId);

	Policy findPlanByName(String name);
	
	@Query("SELECT p FROM Policy p WHERE p.deleted=true")
    public List<Policy> findAll();



}

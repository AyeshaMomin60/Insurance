package com.cg.policy.Insurance.Policy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.policy.Insurance.Policy.model.Plan;
import com.cg.policy.Insurance.Policy.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

	

	User findByUserId(int userId);

	User findByMobile(String mobile);

	//User findByEmail(String );

	User findByEmailAndPassword(String email, String password);

	User findByEmail(String userEmail);
	
	@Query("Select u from User u where u.email=:email")

	public User getUserByEmail(@Param("email") String email);

	
}

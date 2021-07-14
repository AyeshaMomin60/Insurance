package com.cg.policy.Insurance.Policy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.policy.Insurance.Policy.model.Policy;
import com.cg.policy.Insurance.Policy.model.User;

/**
 * @author Aymomin This interface extends {@link JpaRepository} which provides
 *         JPA functionalities for the entity class {@link Policy} that is being
 *         managed.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserId(int userId);

	User findByMobile(String mobile);

	User findByEmail(String email);

	User findByEmailAndPassword(String email, String password);

	List<User> findAllUserByPolicy(Policy planDetails);
	
	@Query("SELECT u FROM User u WHERE u.role!='SUPER-ADMIN'")
    public List<User> findAll();

}

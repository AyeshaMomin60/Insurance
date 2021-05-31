package com.cg.policy.Insurance.Policy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "EnrollmentTable")
public class EnrollmentRequest {

	
	@Id
	@GeneratedValue(
	    strategy = GenerationType.SEQUENCE,
	    generator = "seq_post"
	)
	@SequenceGenerator(
	    name = "seq_post",
	    allocationSize = 1
	)
	private int enrollmentId;
	
//	@Id
//	@GeneratedValue
//	@Column(name = "EnrollmentId")
//	private int enrollmentId;
	
	@OneToOne
	@JoinColumn(name = "userId")
	private User user;

	
	@OneToOne
	@JoinColumn(name = "planId")
	private Plan plan;

	public EnrollmentRequest() {
		super();
		
	}

	public EnrollmentRequest(int enrollmentId, User user, Plan plan) {
		super();
		this.enrollmentId = enrollmentId;
		this.user = user;
		this.plan = plan;
	}

	public int getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	@Override
	public String toString() {
		return "EnrollmentRequest [enrollmentId=" + enrollmentId + ", user=" + user + ", plan=" + plan + "]";
	}

	
	
}

package com.cg.policy.Insurance.Policy.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.UniqueElements;


@Entity
@Table(name = "User_Table")

public class User {
	@Id
	@GeneratedValue
	private int userId;
	
	@Column(name = "password")
    private String password;
	
	@Column(name = "email")
    private String email;

	@Column(name = "Name")
	    private String name;
	 
	 
	 @Column(name = "Mobile")
	    private String mobile;
	
	 @OneToOne
	    @JoinColumn(name = "planId")
	 private Plan plan;
	 
	 
	 private Date memberSince;
	 
    private String nextPaymentDate;
    
	public User() {
		super();
		
	}
	

	public User(int userId, String password, String email, String name, String mobile, Plan plan, Date memberSince,
		String nextPaymentDate) {
	super();
	this.userId = userId;
	this.password = password;
	this.email = email;
	this.name = name;
	this.mobile = mobile;
	this.plan = plan;
	this.memberSince = memberSince;
	this.nextPaymentDate = nextPaymentDate;
}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public Plan getPlan() {
		return plan;
	}


	public void setPlan(Plan plan) {
		this.plan = plan;
	}


	public Date getMemberSince() {
		return memberSince;
	}


	public void setMemberSince(Date memberSince) {
		this.memberSince = memberSince;
	}


	public String getNextPaymentDate() {
		return nextPaymentDate;
	}


	public void setNextPaymentDate(String nextPaymentDate) {
		this.nextPaymentDate = nextPaymentDate;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", email=" + email + ", name=" + name + ", mobile="
				+ mobile + ", plan=" + plan + ", memberSince=" + memberSince + ", nextPaymentDate=" + nextPaymentDate
				+ "]";
	}


	
    
}

package com.cg.policy.Insurance.Policy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Admin_Table")
public class Admin {
	@Id
	@GeneratedValue(
	    strategy = GenerationType.SEQUENCE,
	    generator = "seq_post"
	)
	@SequenceGenerator(
	    name = "seq_post",
	    allocationSize = 1
	)
	private int adminId;
//	@Id
//   @GeneratedValue
//    @Column(name = "adminId")
//    private int adminId;
	
	
	 @Column(name = "Name")
	    private String name;
	 
	 @Column(name = "Email")
	    private String email;
	 
	 
	 @Column(name = "Mobile")
	    private String mobile;
	 
	 @Column(name="password")
	 private String password;

	public Admin() {
		super();
	}

	

	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Admin(int adminId, String name, String mobile, String password) {
		super();
		this.adminId = adminId;
		this.name = name;
		this.mobile = mobile;
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
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

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", name=" + name + ", email=" + email + ", mobile=" + mobile
				+ ", password=" + password + "]";
	}

}

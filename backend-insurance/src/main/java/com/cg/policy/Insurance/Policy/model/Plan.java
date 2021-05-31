package com.cg.policy.Insurance.Policy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Plan_Table")
public class Plan {

	@Id
	@GeneratedValue
	
	private int planId;
	
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "planId")
//	private int planId;
	@Column(name = "Name")
	private String name;
	@Column(name = "cost")
	private Double cost;
	@Column(name = "Details")
	private String details;
	@Column(name = "deductible")
	private int deductible;
	public Plan() {
		super();
	}
	
	public Plan(int planId, String name, Double cost, String details, int deductible) {
		super();
		this.planId = planId;
		this.name = name;
		this.cost = cost;
		this.details = details;
		this.deductible = deductible;
	}

	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public int getDeductible() {
		return deductible;
	}
	public void setDeductible(int deductible) {
		this.deductible = deductible;
	}

	@Override
	public String toString() {
		return "Plan [planId=" + planId + ", name=" + name + ", cost=" + cost + ", details=" + details + ", deductible="
				+ deductible + "]";
	}
	
	
}

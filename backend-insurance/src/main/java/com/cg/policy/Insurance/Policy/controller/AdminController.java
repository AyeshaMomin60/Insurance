package com.cg.policy.Insurance.Policy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.policy.Insurance.Policy.exception.InsuranceException;
import com.cg.policy.Insurance.Policy.model.Admin;
import com.cg.policy.Insurance.Policy.model.Login;
import com.cg.policy.Insurance.Policy.model.Plan;
import com.cg.policy.Insurance.Policy.service.AdminService;
import com.cg.policy.Insurance.Policy.service.PlanService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(path="api")
@CrossOrigin
public class AdminController {
	
	@Autowired
	AdminService service;
	
	@Autowired
	PlanService planService;
	
	@PostMapping("/registerAdmin")
	public ResponseEntity<Admin> addAdmin( @RequestBody Admin admin) {
	Admin result = service.addAdmin(admin);
		//String userName = organization.getOrgName().substring(0, 3) + result.getOrgId();
		Login login = new Login(admin.getAdminId(),admin.getMobile(), admin.getPassword(),'A');
		Login resultLogin = service.addLogin(login);
		//logger.info("New Organization added successfully");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "get login record by username and password", response = Admin.class)
	@GetMapping("admin/login/{email}/{password}")
	public ResponseEntity<Admin> getLogin(@PathVariable String email, @PathVariable String password) throws InsuranceException {
		Admin result=service.getLoginRecord(email, password);
		if(result==null) {
			throw new InsuranceException("not found");
		}
		String message="Login Successful";
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	
	
	@PostMapping("/CreatePlan")
	public ResponseEntity<Plan> addPlan( @RequestBody Plan plan) {
	Plan result = planService.addPlan(plan);
		//logger.info("New plan added successfully");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	

	
	@PutMapping("/updatePlan")
	public ResponseEntity<Plan> updatePlan(@RequestBody Plan plan) throws InsuranceException {
		Plan planDetails =planService.getPlan(plan.getPlanId());
		System.out.print(planDetails);
		if (planDetails == null) {
			throw new InsuranceException("no plan present");
		} 
		planDetails.setCost(plan.getCost());
		planDetails.setDeductible(plan.getDeductible());
		Plan setPlan = planService.UpdatePlan(planDetails);
		System.out.println(setPlan);
		return new ResponseEntity<>(setPlan, HttpStatus.OK);
	}
	

	
	@DeleteMapping(path ={"/DeletePlan/{planId}"})
    public Plan delete(@PathVariable("planId") int planId) {
        return planService.deletePlans(planId);
    }

	
	
	@GetMapping("/viewPlanById/{planId}")
    public Plan viewUserbyEmail(@PathVariable("planId") int planId) throws Exception {
        Plan user = service.viewPlanByPlanId(planId);
        if (user == null) {
            throw new InsuranceException("NOT FOUND");
        } else
            return service.viewPlanByPlanId(planId);
    }

	@GetMapping("/viewPlanByName/{Name}")
    public Plan viewUserbyEmail(@RequestParam("name") String name) throws Exception {
        Plan user = service.viewPlanByName(name);
        if (user == null) {
            throw new InsuranceException("NOT FOUND");
        } else
            return service.viewPlanByName(name);
    }

	@ApiOperation(value = "Show All Plan", response = Plan.class)
	@GetMapping("/AllPlans")
	public ResponseEntity<List<Plan>> AllPlans() {
		 List<Plan> list =  planService.showPlan();
		 return  new ResponseEntity<>(list, HttpStatus.OK);
		
			
		}
}

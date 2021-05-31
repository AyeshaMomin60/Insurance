package com.cg.policy.Insurance.Policy.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.policy.Insurance.Policy.InsurancePolicyApplication;
import com.cg.policy.Insurance.Policy.exception.InsuranceException;
import com.cg.policy.Insurance.Policy.model.EnrollmentRequest;
import com.cg.policy.Insurance.Policy.model.Login;
import com.cg.policy.Insurance.Policy.model.Plan;
import com.cg.policy.Insurance.Policy.model.ResponseModelOfUser;
import com.cg.policy.Insurance.Policy.model.User;
import com.cg.policy.Insurance.Policy.service.PlanService;
import com.cg.policy.Insurance.Policy.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin
public class UserController {
	
	@Autowired
	UserService service;
	
	@Autowired
	PlanService planService;
	
	@PostMapping("/addUser")
	public ResponseEntity<User> registerUser( @RequestBody User user) throws InsuranceException {
		
	String email=user.getEmail();
	
	if(email!=null && !"".equals(email)) {
		User u=service.fetchUserByEmail(email);
		if(u!=null) {
			throw new InsuranceException("User Already Exsist");
		}
	}
	User result = service.addUser(user);
		//String userName = organization.getOrgName().substring(0, 3) + result.getOrgId();
		Login login = new Login(user.getUserId(),user.getMobile(), user.getPassword(),'U');
		Login resultLogin = service.addLogin(login);
		
		//logger.info("New Organization added successfully");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	

	@ApiOperation(value = "get login record by username and password", response = User.class)
	@GetMapping("/user/login/{email}/{password}")
	public ResponseEntity<User> getLogin(@PathVariable("email") String email, @PathVariable("password") String password) throws InsuranceException {
		User result=service.getLoginRecord(email,password);
		System.out.println("Email:"+email);
		System.out.println("password="+password);
		System.out.println("details="+result);
		if(result==null) {
			throw new InsuranceException("not found");
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	@ApiOperation(value = "View Plans", response = Plan.class)
	@GetMapping("/showAllPlans")
	public ResponseEntity<List<Plan>> showAllPlans() {
		 List<Plan> list =  planService.getAllPlan();
		 return  new ResponseEntity<>(list, HttpStatus.OK);
		
			
		}
	
	
	
	@GetMapping(value="/viewUser/{userEmail}",produces=MediaType.APPLICATION_JSON_VALUE,consumes =MediaType.APPLICATION_JSON_VALUE)
    public User viewUserbyEmail(@PathVariable(value = "userEmail") String email) {
		
        User user = service.viewUserby(email);
        System.out.println("Email:"+email);
       System.out.println("user=" +user);
            return user;
    }
//	@GetMapping(value="/viewUserbyEmail/{email}",produces=MediaType.APPLICATION_JSON_VALUE,consumes =MediaType.APPLICATION_JSON_VALUE)
//	ResponseEntity<ResponseModelOfUser> viewUserbyEmail(@PathVariable(value="email") String email) throws Exception {
//		User user = service.viewUserbyEmail(email);
//		System.out.println("user"+user.getName()+user.getMobile()+user.getPassword()+user.getUserId());
//      System.out.println("user=" +user);
//      ModelMapper modelmapper=new ModelMapper();
//      modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//      
//      ResponseModelOfUser createResponseModel=modelmapper.map(user,ResponseModelOfUser.class );
//       //service.viewUserbyEmail(email)
//       if (user == null) {
//           throw new InsuranceException("NOT FOUND");
//       }
//       else {
//           return ResponseEntity.status(HttpStatus.CREATED).body(createResponseModel);
//           }
//   
//	}
//	@ApiOperation(value = "get User By Email", response = User.class)
//	@GetMapping("/viewUserbyEmail/{email}")
//    public ResponseEntity<User> viewUserbyEmail(@PathVariable(value="email") String email) {
//        User user = service.viewUserbyEmail(email);
//       System.out.println("user=" +user);
//       ModelMapper modelmapper=new ModelMapper();
//       modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//       
//       ResponseModelOfUser createResponseModel=modelmapper.map(user,ResponseModelOfUser.class );
//        //service.viewUserbyEmail(email)
//       System.out.println(createResponseModel);
////        if (user == null) {
////            throw new InsuranceException("NOT FOUND");
////        } else
//            return ResponseEntity.status(HttpStatus.CREATED).body(user);
//    }
	
	@ApiOperation(value = "get policy taken by user", response = User.class)
	@GetMapping("/UserTakenPlan/{userId}")
	public ResponseEntity<Plan> ViewEnrollPlan(@PathVariable("userId") int userId) throws InsuranceException {
		User r=service.getEnrollmentDetails(userId);
		if(r==null) {
			throw new InsuranceException("No Policy Is being Taken");
		}
		Plan p=planService.getPlanByPlanId(r.getPlan().getPlanId());
		return new ResponseEntity<>(p,HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "get user By user Id", response = User.class)
	@GetMapping("/UserByUserId/{userId}")
	public ResponseEntity<User> ViewUser(@PathVariable("userId") int userId) throws InsuranceException {
		User r=service.getEnrollmentDetails(userId);
		if(r==null) {
			throw new InsuranceException("No Policy Is being Taken");
		}
		//Plan p=planService.getPlanByPlanId(r.getPlan().getPlanId());
		return new ResponseEntity<>(r,HttpStatus.OK);
	}
	
	
	@PostMapping("/EnrollIntoPlane")
    @ApiOperation("Enrolls a member in a benefit plan")
    public ResponseEntity<EnrollmentRequest> createEnrollment( @RequestBody EnrollmentRequest request) throws Exception {
       
		Plan result=planService.findByPlaneId(request.getPlan().getPlanId());
		System.out.println(result);
		User users=service.findByUserId(request.getUser().getUserId());
		System.out.println(users);
		
		
        if(result==null) {
        	throw new InsuranceException("No plan present");
        }
        if(users==null)
		{
        	throw new InsuranceException("No User present");
        }
        
		EnrollmentRequest r=service.addEnrollmentDetails(request);
		service.setPlanId(users,result);
        
		return new ResponseEntity<>(r,HttpStatus.OK);
    }
	
	
	
	
	

    @DeleteMapping("/cancelPlanRequest/{EnrollmentId}")
    @ApiOperation("Cancels a member's enrollment in a benefit plan")
    public ResponseEntity<Integer> cancelEnrollment( @PathVariable int EnrollmentId) {
    	EnrollmentRequest result=service.findByEnrollmentId(EnrollmentId);
    	//User users=service.findByUserId(request.getUser().getUserId());
    	if(result==null) {
    		throw new Error("No Plan is present");
        }
    	service.DeleteByEnrollmentId(EnrollmentId);
    	
    	
    	return new ResponseEntity<>(0,HttpStatus.OK);
    }
	

    
    @GetMapping("/userDetails/{email:.+}/")
   // @GetMapping("/userDetails/{email:.+}")
    public User UserDetails(@PathVariable(value = "email") String email){
    	User user=service.findUserByEmail(email.toString());
    	System.out.println(user);
    	return user;
    }
	

}
import { Component, OnInit } from '@angular/core';
import { Policy } from '../Policy';
import { PolicyService } from '../policy.service';
import { Router } from '@angular/router';
import { UserAuthenticationService } from 'src/app/service/user-authentication.service';

@Component({
  selector: 'app-create-policy',
  templateUrl: './create-policy.component.html',
  styleUrls: ['./create-policy.component.css']
})
export class CreatePolicyComponent implements OnInit {

  allUser: any;
  successMsg : any;

  policy:Policy={
    planId:0,
    name:'',
    cost:0,
    deductible:0,
    details:''
  }
 
  
  constructor(private UserService: PolicyService,
    userAuth:UserAuthenticationService,
    private router: Router) {}

  ngOnInit(): void {

    this.successMsg = '';
  }
 
  createPolicy(){
    let policy = {
      planId: this.policy.planId,
      name: this.policy.name,
      cost: this.policy.cost,
      deductible: this.policy.deductible,
      details:this.policy.details

    }
   
  this.UserService.createPolicy(policy).subscribe(
    (repsonse) => {
      console.log(repsonse);
      alert("Policy Created Successfully!!");
      this.allUser = repsonse;
      this.successMsg = " Policy Created Successfully. ";
      this.router.navigate(['/list-policy']);
    }
  );
  }

}

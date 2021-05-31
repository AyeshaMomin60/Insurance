import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Policy } from 'src/app/admin/policy/Policy';
import { User } from 'src/app/model/user';
import { UserAuthenticationService } from 'src/app/service/user-authentication.service';
import { PolicyService } from '../policy.service';
import { UserService } from '../user.service';
import { UserPlan } from '../UserPlan';

@Component({
  selector: 'app-view-enroll-policy',
  templateUrl: './view-enroll-policy.component.html',
  styleUrls: ['./view-enroll-policy.component.css']
})
export class ViewEnrollPolicyComponent implements OnInit {

 // allPolicy:any[];
 
 allPolicy:Policy;
  user:UserPlan;
  userId:number;

  constructor(private userService: UserService, 
    private router: Router,
    private policyService:PolicyService,
    private http: HttpClient,
    private route: ActivatedRoute,
   private authService:UserAuthenticationService) {

   }
   

  ngOnInit(): void {
    
    this.userId=+this.authService.getUserId();
    console.log(this.userId);
    this.reloadData(this.userId);
  }


  reloadData(userId:number){
    //console.log(this.userId);
    // this.userService.getUserByUserId(userId).subscribe(
    //   (res:any)=>{
    //   console.log(res)
    //   this.user=res;
    //   },
    //   error=>{
    //      console.log(error);
    //   });

    console.log
    this.policyService.policyByUserId(userId).subscribe(
      (res:any)=>{
      console.log(res)
      this.allPolicy=res;
      
      },
      error=>{
         console.log(error);
      });

  }
  
  

}

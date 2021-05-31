import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PolicyService } from '../policy.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Policy } from '../Policy';

@Component({
  selector: 'app-list-policy',
  templateUrl: './list-policy.component.html',
  styleUrls: ['./list-policy.component.css']
})
export class ListPolicyComponent implements OnInit {

  allPolicy: any = [];
  Policies:Policy[];
  //users: User[];
  allUser: any;
  successMsg : any;
  planId:number;
  Name: string;
  Cost: number;
  deductable:number;
  details:string;
  data: any;
  searchText:'';

  constructor(private policyService: PolicyService, private router: Router,
    private route: ActivatedRoute,
    private http: HttpClient) { }


  ngOnInit(): void {
    this.reloadData();
  
  
  }
  deletePolicy(planId:number){
    console.log()
    this.policyService.deletePlan(planId)
    .subscribe(
      data => {
        console.log(data);
        this.reloadData();
        //this.allPolicy;
      },
      error => console.log(error));
  }

  reloadData(){
    // this.policies = this.policyService.getPolicyList();
    this.policyService.showAllPlans().subscribe(
      res=>
      this.allPolicy=res);
  }
  
}
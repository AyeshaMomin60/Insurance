import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuthenticationService } from 'src/app/service/user-authentication.service';
import { User } from '../User';
import { UserService } from '../user.service';
import { UserLogin } from '../userLogin';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {
  isLoggedin=false;
  customer: UserLogin={
    email:'',
    password:''
  }

  userInfo:User;
  errormsg:any;
  customerUser: any;
  data:any;

  constructor(private userService: UserService,private authService: UserAuthenticationService, private router: Router) { 
    this.errormsg = '';
    
  }

  ngOnInit(): void {
  }
  
  getUserByEmail(){
    let user = {
      email: this.customer.email,
      password: this.customer.password

    }
        this.userService.getUserByEmail(user.email,user.password).subscribe(response => {
          console.log(response);
          this.customerUser = response;
          this.router.navigate(['/user-list-policy']);
          if(this.customerUser.password == user.password)
          {
            this.userService.getUserId(user.email).subscribe(
              (user:User) => {
                this.userInfo = user;
                console.log(this.userInfo);
                this.authService.removeUserId();
                this.authService.setUserId(this.userInfo.userId);
                 console.log(this.userInfo.userId);
                this.authService.removeUserName();
                this.authService.setUserName(this.userInfo.name);
 
              });

            this.router.navigate(['user-list-policy'], 
            { queryParams: { val: this.customerUser.email }});
          }
          else {
           this.errormsg ='invalid password';
          }
        },
        err => {
          if(err.status == 404) {
            this.errormsg = "invalid Mobile number or Password-";
          } 
      }
        );

  }

  
}
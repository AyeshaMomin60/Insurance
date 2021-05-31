import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminLoginComponent } from './admin/admin-login/admin-login.component';
import { AdminRegistrationComponent } from './admin/admin-registration/admin-registration.component';
import { ListPolicyComponent } from './admin/policy/list-policy/list-policy.component';
import { EditPolicyComponent } from './admin/policy/edit-policy/edit-policy.component';
import { HomeComponent } from './home/home.component';
import { UserLoginComponent } from './user/user-login/user-login.component';
import { UserRegistrationComponent } from './user/user-registration/user-registration.component';
import { UserShowPolicyComponent } from './user/user-show-policy/user-show-policy.component';
import { CreatePolicyComponent } from './admin/policy/create-policy/create-policy.component';
import { ViewEnrollPolicyComponent } from './user/view-enroll-policy/view-enroll-policy.component';



const routes: Routes = [
  {path:'home',component:HomeComponent},
  {path:'user-login',component:UserLoginComponent},
  {path:'user-registration',component:UserRegistrationComponent},
  {path:'admin-login',component:AdminLoginComponent},
  {path:'admin-registeration',component:AdminRegistrationComponent},
  {path:'list-policy',component:ListPolicyComponent},
  {path:'edit-policy/:planId',component:EditPolicyComponent},
  {path:'user-list-policy',component:UserShowPolicyComponent},
  {path:'create-policy',component:CreatePolicyComponent},
  {path:'view-enroll-policy',component:ViewEnrollPolicyComponent}
  // {path:'admin-home',component:AdminHomeComponent},
  // {path:'policy-enrollment',component:PolicyEnrollmentComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

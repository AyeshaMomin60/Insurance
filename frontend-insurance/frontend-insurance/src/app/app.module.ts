import { NgModule ,CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule} from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';
import { AdminRegistrationComponent } from './admin/admin-registration/admin-registration.component';
import { UserRegistrationComponent } from './user/user-registration/user-registration.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
//import { UserLoginComponent } from './user/user-login/user-login.component';
import { AdminLoginComponent } from './admin/admin-login/admin-login.component';
import { EditPolicyComponent } from './admin/policy/edit-policy/edit-policy.component';
import { RouterModule } from '@angular/router';
import { UserLoginComponent } from './user/user-login/user-login.component';
import { UserShowPolicyComponent } from './user/user-show-policy/user-show-policy.component';
import { ListPolicyComponent } from './admin/policy/list-policy/list-policy.component';
import { CreatePolicyComponent } from './admin/policy/create-policy/create-policy.component';
import { FilterPipe } from './Pipes/filter.pipe';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { ViewEnrollPolicyComponent } from './user/view-enroll-policy/view-enroll-policy.component';



@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    //UserComponent,
    //AdminComponent,
    UserRegistrationComponent,
    AdminRegistrationComponent,
    UserLoginComponent,
    AdminLoginComponent,
    //PolicyComponent,
    EditPolicyComponent,
    ListPolicyComponent,
  
    UserShowPolicyComponent,
    CreatePolicyComponent,
    FilterPipe,
    ViewEnrollPolicyComponent
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
    Ng2SearchPipeModule
    
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

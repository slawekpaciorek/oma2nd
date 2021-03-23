import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserViewComponent } from './component/user-view/user-view.component';
import { AdminViewComponent } from './component/admin-view/admin-view.component';
import { RouterModule } from '@angular/router';
import { MainViewComponent } from './component/main-view/main-view.component';
import { FooterComponent } from './component/footer/footer.component';
import { HeaderComponent } from './component/header/header.component';
import {HttpClientModule} from '@angular/common/http';
import {CompanyService} from './services/company.service';
import { CompanyListComponent } from './component/company-list/company-list.component';
import {CommonModule} from '@angular/common';
import { CompanyFormComponent } from './component/company-form/company-form.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {LoginComponent} from "./login/login.component";
import {UserComponent} from "./user/user.component";
import {RegisterComponent} from "./register/register.component";
import {HomeComponent} from "./home/home.component";
import {AdminComponent} from "./admin/admin.component";
import {PmComponent} from "./pm/pm.component";
import {httpInterceptorProviders} from "./auth/auth-interceptor";

@NgModule({
  declarations: [
    AppComponent,
    UserViewComponent,
    AdminViewComponent,
    MainViewComponent,
    FooterComponent,
    HeaderComponent,
    CompanyListComponent,
    CompanyFormComponent,
    LoginComponent,
    UserComponent,
    RegisterComponent,
    HomeComponent,
    AdminComponent,
    PmComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      {path: 'user-view', component: UserViewComponent},
      {path: 'admin-view', component: AdminViewComponent},
      {path: 'company', component: CompanyListComponent},
      {path: 'company/all-companies', component: CompanyListComponent},
      {path: 'company/add', component: CompanyFormComponent}
    ]),
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [CompanyService, httpInterceptorProviders],
  bootstrap: [AppComponent],
})
export class AppModule { }

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

@NgModule({
  declarations: [
    AppComponent,
    UserViewComponent,
    AdminViewComponent,
    MainViewComponent,
    FooterComponent,
    HeaderComponent,
    CompanyListComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot([
      {path: 'user-view', component: UserViewComponent},
      {path: 'admin-view', component: AdminViewComponent},
      {path: 'company', component: CompanyListComponent},
      {path: 'company/all-companies', component: CompanyListComponent}
    ]),
    HttpClientModule
  ],
  providers: [CompanyService],
  bootstrap: [AppComponent],
})
export class AppModule { }

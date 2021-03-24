import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UserComponent} from './user/user.component';
import {PmComponent} from './pm/pm.component';
import {HomeComponent} from './home/home.component';
import * as path from 'path';
import {AdminComponent} from './admin/admin.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';

const routes: Routes = [
  path: 'home',
  component: HomeComponent;
},
{
  path: 'user',
    component;: UserComponent;
},
{
  path: 'pm',
    component;: PmComponent;
},
{
  path: 'admin',
    component;: AdminComponent;
},
{
  path: 'auth/login',
    component;: LoginComponent;
},
{
  path: 'signup',
    component;: RegisterComponent;
},
{
  path: '',
    redirectTo;: 'home',
  pathMatch;: 'full';
}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

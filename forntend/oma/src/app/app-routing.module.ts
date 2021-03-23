import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  path: 'home',
  component: HomeComponent
},
{
  path: 'user',
    component: UserComponent
},
{
  path: 'pm',
    component: PmComponent
},
{
  path: 'admin',
    component: AdminComponent
},
{
  path: 'auth/login',
    component: LoginComponent
},
{
  path: 'signup',
    component: RegisterComponent
},
{
  path: '',
    redirectTo: 'home',
  pathMatch: 'full'
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../common/user';
import {map} from 'rxjs/operators';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient : HttpClient, private router: Router) { }

  logUser(user: User) {
    let url = 'http://localhost:8080/login';
    this.httpClient.post(url, user).subscribe(isValid => {
      if(isValid){
        console.log('User logging...');
        sessionStorage.setItem('token', btoa(user.username + ':' + user.password));
        this.router.navigate(['/user-view']);
        console.log('User logged, token : ' + sessionStorage.getItem('token'))
      }else{
        alert("Authentication failed");
    }
    });
  }

}

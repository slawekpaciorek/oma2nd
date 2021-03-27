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

  logUser(name: String, pass: String) {
    let url = 'localhost:8080/login'
    this.httpClient.post<Observable<boolean>>(url, {
        name, pass
    }).subscribe(isValid => {
      if(isValid){
        console.log('User logging...');
        sessionStorage.setItem('token', btoa(name + ':' + pass));
        this.router.navigate(['/user-view']);
        console.log('User logged, token : ' + sessionStorage.getItem('token'))
      }else{
        alert("Authentication failed");
    }
    });
  }

}

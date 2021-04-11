import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../common/user';
import {Router} from '@angular/router';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient : HttpClient, private router: Router) { }

  logUser(user: User) {
    let username = user.username;
    let password = user.password
    let url = 'http://localhost:8080/login';
    return this.httpClient.post<any>(url, {username, password}).pipe(map(userData => {
        sessionStorage.setItem("username", username);
        let JWTtoken = "Bearer " + userData.token;
        sessionStorage.setItem("token" , JWTtoken);
        this.router.navigate(["user-view"]);
        return userData;
    }));
  }

  isUserLoggedIn(){
    let user = sessionStorage.getItem("user");
    return !(user===null);
  }

  logOut(){
    sessionStorage.removeItem("user");
    sessionStorage.removeItem("token");
    this.router.navigate(['']);
  }

}

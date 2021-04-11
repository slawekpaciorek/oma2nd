import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationIntereceptorService implements HttpInterceptor{

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if(sessionStorage.getItem("username") && sessionStorage.getItem("token")){
      req = req.clone({
        setHeaders:{
          Authorization : sessionStorage.getItem('token')
        }
      })
    }
    return next.handle(req);
  }


}

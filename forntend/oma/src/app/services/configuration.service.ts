import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ConfigurationService {

  constructor(private httpClient : HttpClient, private router : Router) { }

  isConfigured: boolean = false;

  demoSetup(): void{
    if(this.isConfigured===false) {
      console.log("start demo config");
      this.httpClient.get("http://localhost:8080/demo").subscribe();
      console.log("finish demo config");
      this.isConfigured = true;
    }
    this.router.navigate(["/admin-view"]);
  }



}

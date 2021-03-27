import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {User} from '../../common/user';
import {LoginService} from '../../service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userForm : FormGroup;

  constructor(private formBuilder : FormBuilder, private loginService : LoginService) { }

  ngOnInit(): void {
    sessionStorage.setItem('token', '');
    this.userForm = this.formBuilder.group({
      user : this.formBuilder.group({
        userName: [''],
        password: ['']
      })
    })
  }

  submitLogin(): void{
    let username = this.userForm.get('user').value.username;
    let password = this.userForm.get('user').value.password
    this.loginService.logUser(username,password);
  }

}

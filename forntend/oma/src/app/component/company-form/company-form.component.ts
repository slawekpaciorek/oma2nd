import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Company} from '../../common/company';
import {Address} from '../../common/address';
import {User} from '../../common/user';
import {CompanyService} from '../../services/company.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-company-form',
  templateUrl: './company-form.component.html',
  styleUrls: ['./company-form.component.css']
})
export class CompanyFormComponent implements OnInit {

  companyFormGroup: FormGroup;

  constructor(private formBuilder: FormBuilder, private companyService: CompanyService, private router: Router) {
  }

  ngOnInit(): void {

    this.companyFormGroup = this.formBuilder.group({
      company: this.formBuilder.group({
        companyName: [''],
        taxIdNumber: ['']
      }),
      address: this.formBuilder.group({
        streetNameAndNumber: [''],
        zipCode: [''],
        city: [''],
        mobilePhoneNumber:['']
      }),
      user: this.formBuilder.group({
        username: [''],
        name: [''],
        mobilePhoneNumber:['']
      })
    });

  }

  onSubmit(){
    console.log("Handling submit button");

    let company = new Company(
      null,
        this.companyFormGroup.get('company').value.companyName,
        this.companyFormGroup.get('company').value.taxIdNumber,
      null
    );

    let address = new Address(
      null,
        this.companyFormGroup.get('address').value.streetNameAndNumber,
        this.companyFormGroup.get('address').value.zipCode,
        this.companyFormGroup.get('address').value.city,
        this.companyFormGroup.get('address').value.mobilePhoneNumber
    );

    let user = new User(
      null,
        this.companyFormGroup.get('user').value.username,
        this.companyFormGroup.get('user').value.name,
        null,
        this.companyFormGroup.get('user').value.mobilePhoneNumber,
      null
    );

    user.privileges = "manager";
    company.address = address;
    company.users = [user];
    this.companyService.saveCompany(company).subscribe(result => company = result);
    console.log(company.name + " saved with success");
    this.router.navigate(['/admin-view'])
  }

}

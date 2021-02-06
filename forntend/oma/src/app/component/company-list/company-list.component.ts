import { Component, OnInit } from '@angular/core';
import {CompanyService} from '../../services/company.service';
import {Company} from '../../common/company';

@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.css']
})
export class CompanyListComponent implements OnInit {

  public companies: Company[];

  constructor(private companyService: CompanyService) { }

  ngOnInit(): void {
    this.listCompanies();
  }

  private listCompanies() {
    this.companyService.getCompanyList().subscribe(
      value => this.companies = value
    );
  }
}

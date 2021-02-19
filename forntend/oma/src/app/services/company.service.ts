import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Company, CompanyAdapter} from '../common/company';
import {map} from 'rxjs/operators';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private httpHeaders = new HttpHeaders({
    'Content-Type' : 'application/json',
    'Cache-Control': 'no-cache'
  });

  private baseURL = 'http://localhost:8080/company';

  constructor(private httpClient: HttpClient, private adapter: CompanyAdapter) { }

  getCompanyList(): Observable<Company[]>{
    const tempURL = '/all-companies';
    return this.httpClient.get(`${this.baseURL}${tempURL}`).pipe(
      map((data: any[]) => data.map((item) => this.adapter.adapt(item)))
    );
  }

  saveCompany(company: Company): Observable<Company>{
    let url = 'http://localhost:8080/company/add';
    let options = {
      headers: this.httpHeaders
    };
    return this.httpClient.post<Company>(url, company, options);
  }

}


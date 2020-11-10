import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Company, CompanyAdapter} from '../common/company';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private baseURL = 'http://localhost:8080/company';

  constructor(private httpClient: HttpClient, private adapter: CompanyAdapter) { }

  getCompanyList(): Observable<Company[]>{
    const tempURL = '/all-companies';
    return this.httpClient.get(`${this.baseURL}${tempURL}`).pipe(
      map((data: any[]) => data.map((item) => this.adapter.adapt(item)))
    );
  }
}


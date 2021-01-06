import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  constructor(private httpClient: HttpClient) { }

  private handleError(error : HttpErrorResponse){

    if (error.error instanceof ErrorEvent){
      console.error("Error occurred : " + error.error.message);
    }
    else {
      console.error(
        `Server error with status : ${error.status}, ` +
        `Body was : ${error.error}`);
    }

    return throwError(
      `Something went wrong, pleas contact with admin`
    )

  }
}

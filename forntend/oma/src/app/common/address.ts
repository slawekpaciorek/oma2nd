import {Injectable} from '@angular/core';
import {Adapter} from '../core/adapter';

export class Address {
  id: number;
  streetNameAndNumber: string;
  zipCode: string;
  city: string;
  mobilePhoneNumber: number;

  constructor(id?: number, streetName?: string, zipCode?: string, city?: string, mobilePhone?: number) {
    if(id){
      this.id = id;
    }
    this.streetNameAndNumber = streetName;
    this.zipCode = zipCode;
    this.city = city;
    this.mobilePhoneNumber = mobilePhone;
  }

}

@Injectable({
  providedIn: "root"
})
export class AddressAdapter implements Adapter<Address>{
  adapt(item: any): Address {
    return new Address(item.id, item.streetNameAndNumber, item.zipCode, item.city, item.mobilePhone);
  }

}

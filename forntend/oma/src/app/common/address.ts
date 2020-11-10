import {Injectable} from '@angular/core';
import {Adapter} from '../core/adapter';

export class Address {
  private _id: number;
  private _streetNameAndNumber: string;
  private _zipCode: string;
  private _city: string;
  private _mobilePhoneNumber: number;

  constructor(id: number, streetName: string, zipCode: string, city: string, mobilePhone: number) {
    this._id = id;
    this._streetNameAndNumber = streetName;
    this._zipCode = zipCode;
    this._city = city;
    this._mobilePhoneNumber = mobilePhone;
  }


  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get streetNameAndNumber(): string {
    return this._streetNameAndNumber;
  }

  set streetNameAndNumber(value: string) {
    this._streetNameAndNumber = value;
  }

  get zipCode(): string {
    return this._zipCode;
  }

  set zipCode(value: string) {
    this._zipCode = value;
  }

  get city(): string {
    return this._city;
  }

  set city(value: string) {
    this._city = value;
  }

  get mobilePhoneNumber(): number {
    return this._mobilePhoneNumber;
  }

  set mobilePhoneNumber(value: number) {
    this._mobilePhoneNumber = value;
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

import {Address, AddressAdapter} from './address';
import {User} from './user';
import {Order} from './order';
import {DeliveryPoint} from './delivery-point';
import {Injectable} from '@angular/core';
import {Adapter} from '../core/adapter';

export class Company {
  private _id: number;
  private _name: string;
  private _taxNumberId: number;
  private _address: Address;
  private _users: User[];
  private _orders: Order[];
  private _deliveryPoint: DeliveryPoint;

  constructor(id: number, name: string, taxNumberId: number, address: any) {
    this._id = id;
    this._name = name;
    this._taxNumberId = taxNumberId;
    this._address = new AddressAdapter().adapt(address);
  }


  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get taxNumberId(): number {
    return this._taxNumberId;
  }

  set taxNumberId(value: number) {
    this._taxNumberId = value;
  }

  get address(): Address {
    return this._address;
  }

  set address(value: Address) {
    this._address = value;
  }
}

@Injectable({
  providedIn: "root"})

export class CompanyAdapter implements Adapter<Company>{
  adapt(item: any): Company {
    return new Company(item.id, item.name, item.taxNumberId, item.address)
  }

}

import {User} from './user';
import {Address} from './address';
import {Company} from './company';
import {Order} from './order';

export class DeliveryPoint {

  private _id: number;
  private _name: string;
  private _createdBy: User;
  private _address: Address;
  private _company: Company;
  private _orders: Order[];


  constructor(id: number, name: string, createdBy: User, address: Address, company: Company, orders: Order[]) {
    this._id = id;
    this._name = name;
    this._createdBy = createdBy;
    this._address = address;
    this._company = company;
    this._orders = orders;
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

  get createdBy(): User {
    return this._createdBy;
  }

  set createdBy(value: User) {
    this._createdBy = value;
  }

  get address(): Address {
    return this._address;
  }

  set address(value: Address) {
    this._address = value;
  }

  get company(): Company {
    return this._company;
  }

  set company(value: Company) {
    this._company = value;
  }

  get orders(): Order[] {
    return this._orders;
  }

  set orders(value: Order[]) {
    this._orders = value;
  }
}

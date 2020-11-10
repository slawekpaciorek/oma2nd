import {Company} from './company';
import {DeliveryPoint} from './delivery-point';
import {Order} from './order';

export class User {

  private _id: number;
  private _firstName: string;
  private _username: string;
  private _password: string;
  private _mobilePhone: number;
  private _privileges: string;
  private _company: Company;
  private _deliveryPoints: DeliveryPoint[];
  private _orders: Order[];


  constructor(id: number, firstName: string, username: string, password: string, mobilePhone: number, privileges: string, company: Company, deliveryPoints: DeliveryPoint[], orders: Order[]) {
    this._id = id;
    this._firstName = firstName;
    this._username = username;
    this._password = password;
    this._mobilePhone = mobilePhone;
    this._privileges = privileges;
    this._company = company;
    this._deliveryPoints = deliveryPoints;
    this._orders = orders;
  }


  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get firstName(): string {
    return this._firstName;
  }

  set firstName(value: string) {
    this._firstName = value;
  }

  get username(): string {
    return this._username;
  }

  set username(value: string) {
    this._username = value;
  }

  get password(): string {
    return this._password;
  }

  set password(value: string) {
    this._password = value;
  }

  get mobilePhone(): number {
    return this._mobilePhone;
  }

  set mobilePhone(value: number) {
    this._mobilePhone = value;
  }

  get privileges(): string {
    return this._privileges;
  }

  set privileges(value: string) {
    this._privileges = value;
  }

  get company(): Company {
    return this._company;
  }

  set company(value: Company) {
    this._company = value;
  }

  get deliveryPoints(): DeliveryPoint[] {
    return this._deliveryPoints;
  }

  set deliveryPoints(value: DeliveryPoint[]) {
    this._deliveryPoints = value;
  }

  get orders(): Order[] {
    return this._orders;
  }

  set orders(value: Order[]) {
    this._orders = value;
  }
}

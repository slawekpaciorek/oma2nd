import {Company} from './company';
import {DeliveryPoint} from './delivery-point';
import {User} from './user';
import {Product} from './product';

export class Order {
  private _id: number;
  private _company: Company;
  private _deliveryPoint: DeliveryPoint;
  private _products: Product;
  private _createdBy: User;
  private _approvedBy: User;
  private _status: string;
  private _createdAt: string;
  private _summaryValue: number;
  private _info:string


  constructor(id: number, company: Company, deliveryPoint: DeliveryPoint, products: Product, createdBy: User, approvedBy: User, status: string, createdAt: string, summaryValue: number, info: string) {
    this._id = id;
    this._company = company;
    this._deliveryPoint = deliveryPoint;
    this._products = products;
    this._createdBy = createdBy;
    this._approvedBy = approvedBy;
    this._status = status;
    this._createdAt = createdAt;
    this._summaryValue = summaryValue;
    this._info = info;
  }


  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get company(): Company {
    return this._company;
  }

  set company(value: Company) {
    this._company = value;
  }

  get deliveryPoint(): DeliveryPoint {
    return this._deliveryPoint;
  }

  set deliveryPoint(value: DeliveryPoint) {
    this._deliveryPoint = value;
  }

  get products(): Product {
    return this._products;
  }

  set products(value: Product) {
    this._products = value;
  }

  get createdBy(): User {
    return this._createdBy;
  }

  set createdBy(value: User) {
    this._createdBy = value;
  }

  get approvedBy(): User {
    return this._approvedBy;
  }

  set approvedBy(value: User) {
    this._approvedBy = value;
  }

  get status(): string {
    return this._status;
  }

  set status(value: string) {
    this._status = value;
  }

  get createdAt(): string {
    return this._createdAt;
  }

  set createdAt(value: string) {
    this._createdAt = value;
  }

  get summaryValue(): number {
    return this._summaryValue;
  }

  set summaryValue(value: number) {
    this._summaryValue = value;
  }

  get info(): string {
    return this._info;
  }

  set info(value: string) {
    this._info = value;
  }
}

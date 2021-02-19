import {Company} from './company';
import {DeliveryPoint} from './delivery-point';
import {User} from './user';
import {Product} from './product';

export class Order {
  id: number;
  company: Company;
  deliveryPoint: DeliveryPoint;
  products: Product;
  createdBy: User;
  approvedBy: User;
  status: string;
  createdAt: string;
  summaryValue: number;
  info:string


  constructor(id: number, company: Company, deliveryPoint: DeliveryPoint, products: Product, createdBy: User, approvedBy: User, status: string, createdAt: string, summaryValue: number, info: string) {
    this.id = id;
    this.company = company;
    this.deliveryPoint = deliveryPoint;
    this.products = products;
    this.createdBy = createdBy;
    this.approvedBy = approvedBy;
    this.status = status;
    this.createdAt = createdAt;
    this.summaryValue = summaryValue;
    this.info = info;
  }

}

import {User} from './user';
import {Address} from './address';
import {Company} from './company';
import {Order} from './order';

export class DeliveryPoint {

  id: number;
  name: string;
  createdBy: User;
  address: Address;
  company: Company;
  orders: Order[];


  constructor(id: number, name: string, createdBy: User, address: Address, company: Company, orders: Order[]) {
    this.id = id;
    this.name = name;
    this.createdBy = createdBy;
    this.address = address;
    this.company = company;
    this.orders = orders;
  }

}

import {Company} from './company';
import {DeliveryPoint} from './delivery-point';
import {Order} from './order';

export class User {

  id: number;
  firstName: string;
  username: string;
  password: string;
  mobilePhone: number;
  privileges: string;
  company: Company;
  deliveryPoints: DeliveryPoint[];
  orders: Order[];


  constructor(id?: number, username?: string, firstName?: string, password?: string, mobilePhone?: number, privileges?: string, company?: Company, deliveryPoints?: DeliveryPoint[], orders?: Order[]) {
    if(id){
      this.id = id;
    }
    if(password){
      this.password = password;
    }
    if(privileges) {
      this.privileges = privileges;
    }
    if(company) {
      this.company = company;
    }
    if(deliveryPoints) {
      this.deliveryPoints = deliveryPoints;
    }
    if(orders) {
      this.orders = orders;
    }
    this.firstName = firstName;
    this.username = username;
    this.mobilePhone = mobilePhone;
  }

}

import {Address, AddressAdapter} from './address';
import {User} from './user';
import {Order} from './order';
import {DeliveryPoint} from './delivery-point';
import {Injectable} from '@angular/core';
import {Adapter} from '../core/adapter';

export class Company {
  id: number;
  name: string;
  taxNumberId: number;
  address: Address;
  users: User[];
  orders: Order[];
  deliveryPoint: DeliveryPoint;

  constructor(id?: number, companyName?: string, taxNumberId?: number, address?: any) {
      if(id&&address){
        this.id = id;
        this.address = address;
      }
      this.name = companyName;
      this.taxNumberId = taxNumberId;
  }
}

@Injectable({
  providedIn: "root"})

export class CompanyAdapter implements Adapter<Company>{
  adapt(item: any): Company {
    return new Company(item.id, item.name, item.taxNumberId, item.address)
  }

}

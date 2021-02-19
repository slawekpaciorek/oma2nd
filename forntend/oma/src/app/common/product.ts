export class Product {

  id: number;
  name: string
  catNumber: string;
  tradeNumber: string;

  constructor(id: number, name: string, catNumber: string, tradeNumber: string) {
    this.id = id;
    this.name = name;
    this.catNumber = catNumber;
    this.tradeNumber = tradeNumber;
  }

}

export class Product {

  private _id: number;
  private _name: string
  private _catNumber: string;
  private _tradeNumber: string;


  constructor(id: number, name: string, catNumber: string, tradeNumber: string) {
    this._id = id;
    this._name = name;
    this._catNumber = catNumber;
    this._tradeNumber = tradeNumber;
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

  get catNumber(): string {
    return this._catNumber;
  }

  set catNumber(value: string) {
    this._catNumber = value;
  }

  get tradeNumber(): string {
    return this._tradeNumber;
  }

  set tradeNumber(value: string) {
    this._tradeNumber = value;
  }
}

import {Category} from "./category.model";

export class OperationRecord {
  constructor(
    public id?: number,
    public category?: Category,
    public date?: Date,
    public spending?: boolean,
    public amount?: number,
    public currencyCode?: string,
    public cashBack?: number,
  ) {}
}

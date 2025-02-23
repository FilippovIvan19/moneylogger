import { Injectable } from '@angular/core';
import {BaseService} from "./base.service";
import {Category} from "../models/category.model";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CategoryService extends BaseService<Category>{

//   constructor(protected http: HttpClient) {
//     super(http, Category);
//     this.url = 'http://localhost:8080/categories';
//   }

  override getUrl(): string {
    return 'http://localhost:8080/categories';
  }

  override getTypeName(): string {
    return 'Category';
  }
}

import { Injectable } from '@angular/core';
import {BaseService} from "./base.service";
import {OperationRecord} from "../models/operation-record.model";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class OperationRecordService extends BaseService<OperationRecord> {

//   constructor(protected http: HttpClient) {
//     super(http, Record);
//     console.log('record service created');
//     this.url = 'http://localhost:8080/operationRecords';
//   }

  override getUrl(): string {
    return 'http://localhost:8080/operationRecords';
  }

  override getTypeName(): string {
    return 'OperationRecord';
  }
}

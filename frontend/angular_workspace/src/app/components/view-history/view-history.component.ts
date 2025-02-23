import { Component, OnInit } from '@angular/core';
import {NgFor} from "@angular/common";
import {OperationRecordService} from "../../services/operation-record.service";
import {OperationRecord} from '../../models/operation-record.model';

@Component({
  selector: 'app-view-history',
  imports: [NgFor],
  templateUrl: './view-history.component.html',
  styleUrl: './view-history.component.less'
})
export class ViewHistoryComponent implements OnInit {
  operationRecords: OperationRecord[] = [];

  constructor(private operationRecordService: OperationRecordService) { }

  ngOnInit(): void {
    this.operationRecordService.getAll().subscribe(response => {
      this.operationRecords = response.body ?? [];
    });
    console.log(this.operationRecords);
  }

}

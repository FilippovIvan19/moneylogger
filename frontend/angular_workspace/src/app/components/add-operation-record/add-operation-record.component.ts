import { Component, OnInit } from '@angular/core';
import {NgFor, DatePipe } from "@angular/common";
import {OperationRecord} from '../../models/operation-record.model';
import {OperationRecordService} from "../../services/operation-record.service";
import {CategoryService} from "../../services/category.service";
import {Category} from '../../models/category.model';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-add-operation-record',
  imports: [
    ReactiveFormsModule,
    NgFor
  ],
  providers: [DatePipe],
  templateUrl: './add-operation-record.component.html',
  styleUrls: ['./add-operation-record.component.less']
})
export class AddOperationRecordComponent implements OnInit {
  possibleCategories: Category[] = [];

  applyForm = new FormGroup({
    id:           new FormControl(),
    category:     new FormControl(0),
    date:         new FormControl(''),
    spending:     new FormControl('true'),
    amount:       new FormControl(0),
    currencyCode: new FormControl('RUR'),
    cashBack:     new FormControl(0),
  });

  constructor(private operationRecordService: OperationRecordService,
              private categoryService: CategoryService,
              private datePipe: DatePipe) {
  }

  ngOnInit(): void {
    this.applyForm.get('date')?.setValue(this.datePipe.transform(Date.now(), 'yyyy-MM-dd'));
    this.categoryService.getAll().subscribe(response => this.possibleCategories = response.body ?? []);
  }


  save(): void {
//     console.log(this.datePipe.transform(Date.now(), 'yyyy-MM-dd'));
    // console.log(this.record.date.getFullYear());
    // console.log(this.record.amount);
    // console.log(this.record.cashBack);
    // console.log(this.record.currencyCode);
    // console.log(this.record.categoryId);
    // console.log(this.record.amount);
    // console.log(this.record.spending);

//     console.log(Object.values(this.applyForm.value));
    let operationRecord: OperationRecord = new OperationRecord(...Object.values(this.applyForm.value));
//     console.log(record);

    this.operationRecordService.create(operationRecord).subscribe(_ => {}
      /*console.log(_)*/);
  }

//   getDate(target: EventTarget): Date {
//     return (target as HTMLInputElement).valueAsDate;
//   }
//
//   getFloat(target: EventTarget): number {
//     return parseFloat((target as HTMLInputElement).value);
//   }
//
//   getInt(target: EventTarget): number {
//     return parseInt((target as HTMLInputElement).value);
//   }
//
//   getBool(target: EventTarget): boolean {
//     return (target as HTMLInputElement).value == "true";
//   }

}

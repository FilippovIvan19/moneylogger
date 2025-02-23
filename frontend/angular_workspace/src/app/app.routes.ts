import { Routes } from '@angular/router';

// export const routes: Routes = [];



// import { NgModule } from '@angular/core';
// import { CommonModule } from '@angular/common';
// import { RouterModule, Routes } from '@angular/router';
import {AddOperationRecordComponent} from "./components/add-operation-record/add-operation-record.component";
import {ViewHistoryComponent} from "./components/view-history/view-history.component";
//
//
export const routes: Routes = [
  { path: 'moneyLogger/view', component: ViewHistoryComponent },
  { path: 'moneyLogger/add', component: AddOperationRecordComponent },
  { path: '', redirectTo: 'moneyLogger/add', pathMatch: 'full' },
  { path: 'moneyLogger', redirectTo: 'moneyLogger/add', pathMatch: 'full' },
];

// export default routes;
//
// @NgModule({
//   declarations: [],
//   imports: [RouterModule.forRoot(routes)],
//   exports: [RouterModule]
// })
// export class AppRoutingModule { }

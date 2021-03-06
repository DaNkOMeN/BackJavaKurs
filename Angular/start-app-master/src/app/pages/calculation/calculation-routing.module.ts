import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CalculationComponent} from './calculation/calculation.component';


const routes: Routes = [
  {
    path: '',
    component: CalculationComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CalculationRoutingModule { }

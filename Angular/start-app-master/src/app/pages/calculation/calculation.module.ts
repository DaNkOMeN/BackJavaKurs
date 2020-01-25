import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CalculationRoutingModule } from './calculation-routing.module';
import { CalculationComponent } from './calculation/calculation.component';
import { FormsModule } from '@angular/forms';
import { FormControlsModule } from '../../core/form-controls/form-controls.module';
import { NgSelectModule } from '@ng-select/ng-select';
import { CheckboxModule, WavesModule, ButtonsModule } from 'angular-bootstrap-md';
import { DataTablesModule } from 'angular-datatables';


@NgModule({
  declarations: [CalculationComponent],
  imports: [
    CommonModule,
    CalculationRoutingModule,
    FormsModule,
    FormControlsModule,
    NgSelectModule,
    CheckboxModule, 
    WavesModule, 
    ButtonsModule,
    DataTablesModule
  ]
})
export class CalculationModule {
}

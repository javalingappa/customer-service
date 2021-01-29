import { NgModule } from '@angular/core';

import { OptionsComponent } from './options/options.component';
import { DeliveryRoutingModule } from './delivery.routing.module';
import { AppMaterialModule } from '../material/appmaterial.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { ConfirmationComponent } from './confirmation/confirmation.component';
import { CommonModule } from '@angular/common';


@NgModule({
  declarations: [OptionsComponent, ConfirmationComponent],
  imports: [
    CommonModule,
    DeliveryRoutingModule,
    AppMaterialModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ]
})
export class DeliveryModule { }

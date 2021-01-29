import { NgModule } from '@angular/core';

import { FromComponent } from './from/from.component';
import { ToComponent } from './to/to.component';
import { AddressRoutingModule } from './address.routing.module';

import { AppMaterialModule } from '../material/appmaterial.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [FromComponent, ToComponent],
  imports: [
    AddressRoutingModule,
    AppMaterialModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class AddressModule { }

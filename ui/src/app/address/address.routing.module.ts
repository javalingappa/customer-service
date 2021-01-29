import { NgModule} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { FromComponent } from './from/from.component';
import { ToComponent } from './to/to.component';
import { CommonModule } from '@angular/common';
import { ToaddressAuthGuardService } from './services/toaddress-auth-guard.service';

const routes: Routes = [
  {
    path: 'from',
    component: FromComponent
  },
  {
      path: 'to',
      component: ToComponent,
      canActivate: [ToaddressAuthGuardService]
  },
  {
    path: '',
    redirectTo: 'from',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [ CommonModule, RouterModule.forChild(routes)],
  exports: [ RouterModule ]
})
export class AddressRoutingModule {}
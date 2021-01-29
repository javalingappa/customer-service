import { NgModule} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OptionsComponent } from './options/options.component';
import { OptionsAuthGuardService } from './services/options-auth-guard.service';
import { ConfirmationComponent } from './confirmation/confirmation.component';

const routes: Routes = [
  {
      path: 'options',
      component: OptionsComponent,
      canActivate: [OptionsAuthGuardService]
  },
  {
    path: 'order-confirmation',
    component: ConfirmationComponent,
    //canActivate: [OptionsAuthGuardService]
  },
  {
    path: '',
    redirectTo: 'options',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [ RouterModule ]
})
export class DeliveryRoutingModule {}
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {
    path: 'delivery',
    loadChildren: () => import('./delivery/delivery.module').then(m=>m.DeliveryModule)
  },
  {
    path: 'address',
    loadChildren: () => import('./address/address.module').then(m=>m.AddressModule)
  },
  {
    path: '',
    redirectTo: 'address',
    pathMatch: 'full'
  },
  {
    path: '**',
    redirectTo: 'address'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

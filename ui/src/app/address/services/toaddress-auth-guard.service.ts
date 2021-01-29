import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class ToaddressAuthGuardService implements CanActivate {

  constructor(private _router: Router, private _snackbar: MatSnackBar) { }

  canActivate(_route: ActivatedRouteSnapshot): boolean {
    if(!localStorage.customerId) {
      this._snackbar.open('You are unauthorized to access to page.', null, { duration: 3*1000});
      this._router.navigate(['./address/from']);
      return false;
    }
    return true;
  }
}

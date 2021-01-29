import { Component, OnInit } from '@angular/core';

import { Address } from '../address';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FedexService } from '../services/fedex.service';
import { Customer } from 'src/app/model/customer.model';
import { AddressBackend } from 'src/app/model/address.model';

@Component({
  selector: 'from-address',
  templateUrl: './from.component.html',
  styleUrls: ['./from.component.scss']
})
export class FromComponent implements OnInit {

  fromAddressGroup: FormGroup;
  public isSavedInStorage: boolean = false;
  customer: Customer;
  addressBackend: AddressBackend;
  customerId: string = '';
  constructor(private fedexService: FedexService, private _formBuilder: FormBuilder, private _router: Router, private _snackbar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.fromAddressGroup = this._formBuilder.group({
      name: ['', Validators.required],
      mobile: ['', Validators.compose([Validators.required, Validators.minLength(10), Validators.maxLength(10), Validators.pattern(/[1-9]{1,1}[0-9]{9,9}/)])],
      postalcode: ['', Validators.compose([Validators.required, Validators.minLength(6), Validators.maxLength(6), Validators.pattern(/[1-9]{1,1}[0-9]{5}/)])],
      locality: ['', Validators.required],
      address: ['', Validators.required],
      city: ['', Validators.required],
      state: ['', Validators.required],
      landMark: [''],
      alternateMobile: ['', Validators.compose([Validators.minLength(10), Validators.maxLength(10), Validators.pattern(/[1-9]{1,1}[0-9]{9,9}/)])],
      addressType: ['Home', Validators.required]
    });

    /*if(localStorage.f_a) {
         try{
           const f_a = JSON.parse(localStorage.getItem('f_a'));
           this.fromAddressGroup.setValue(f_a);
           this.isSavedInStorage = true;
         }catch(e) {
           console.warn("Error", e.messaage);
         }
       } */
  }

  fromAddressSubmit() {
    if (this.fromAddressGroup.valid) {
      this.customer = new Customer();
      this.customer.altPhone = this.fromAddressGroup.value.alternateMobile;
      this.customer.phone = this.fromAddressGroup.value.mobile;
      this.customer.name = this.fromAddressGroup.value.name;
      this.customer.customerType = 'FROM';
      this.fedexService.createCustomer(this.customer).subscribe(data => {
        console.log("Customer Added to database success");
        console.log("customer data -> " + data);
        this.customerId = data.customerId;
        localStorage.setItem('customerId', data.customerId);
        if (this.customerId) {
          this.addressBackend = new AddressBackend();
          this.addressBackend.address = this.fromAddressGroup.value.address;
          this.addressBackend.addressType = this.fromAddressGroup.value.addressType;
          this.addressBackend.cityTownDistrict = this.fromAddressGroup.value.city;
          this.addressBackend.landmark = this.fromAddressGroup.value.landMark;
          this.addressBackend.locality = this.fromAddressGroup.value.locality;
          this.addressBackend.postalCode = this.fromAddressGroup.value.postalcode;
          this.addressBackend.state = this.fromAddressGroup.value.state;
          this.fedexService.createAddress(this.addressBackend,this.customerId).subscribe(data => {
            console.log("Customer From Address Added to database success");
            console.log("customer From Address data -> " + data);
  
            this._snackbar.open("Customer Added to database success", null, {
              duration: 3 * 1000
            });
            this._router.navigate(['/address/to']);
          },
            error => {
              console.log("ERROR:: Customer from address Added to database failed");
            });
  
        }
      },
        error => {
          console.log("ERROR:: Customer Added to database failed");
        });
      console.log(this.customerId);
 
    }
  }

  clearStorage() {
    this.fromAddressGroup.reset();
    this.fromAddressGroup.patchValue({ addressType: 'Home' });
    localStorage.removeItem('customerId');
    this.isSavedInStorage = false;
  }

}

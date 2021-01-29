import { Component, OnInit } from '@angular/core';

import { Address } from '../address';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Customer } from 'src/app/model/customer.model';
import { AddressBackend } from 'src/app/model/address.model';
import { FedexService } from '../services/fedex.service';

@Component({
  selector: 'to-page',
  templateUrl: './to.component.html',
  styleUrls: ['./to.component.scss']
})

export class ToComponent implements OnInit {

  toAddressGroup: FormGroup;
  public isSavedInStorage: boolean = false;
  customer: Customer;
  addressBackend: AddressBackend;
  constructor( private fedexService: FedexService , private _formBuilder: FormBuilder, private _router: Router, private _snackbar: MatSnackBar) { 
  }

  ngOnInit(): void {
    this.toAddressGroup = this._formBuilder.group({
      name: ['', Validators.required],
      mobile: ['', Validators.compose([Validators.required, Validators.minLength(10), Validators.maxLength(10), Validators.pattern(/[1-9]{1,1}[0-9]{9,9}/)])],
      postalcode: ['', Validators.compose([Validators.required, Validators.minLength(6), Validators.maxLength(6), Validators.pattern(/[1-9]{1,1}[0-9]{5}/)])],
      locality: ['', Validators.required],
      address: ['', Validators.required],
      city: ['', Validators.required],
      state: ['', Validators.required],
      landMark: [''],
      alternateMobile: ['', Validators.compose([Validators.minLength(10), Validators.maxLength(10), Validators.pattern(/[1-9]{1,1}[0-9]{9,9}/)])],
      addressType: ['Work', Validators.required]
    });
  }

  toAddressSubmit() {
    if(this.toAddressGroup.valid) {
      this.customer = new Customer();
      this.customer.altPhone=this.toAddressGroup.value.alternateMobile;
      this.customer.phone=this.toAddressGroup.value.mobile;
      this.customer.name=this.toAddressGroup.value.name;
      this.customer.customerType='TO';
      this.fedexService.createCustomer(this.customer).subscribe(data =>{
        console.log("Customer Added to database success");
        console.log("customer data -> "+data);
        const customerId =  data.customerId;
        console.log(customerId);
        if(customerId) {
          this.addressBackend= new AddressBackend();
          this.addressBackend.address = this.toAddressGroup.value.address;
          this.addressBackend.addressType= this.toAddressGroup.value.addressType;
          this.addressBackend.cityTownDistrict= this.toAddressGroup.value.city;
          this.addressBackend.landmark= this.toAddressGroup.value.landMark;
          this.addressBackend.locality= this.toAddressGroup.value.locality;
          this.addressBackend.postalCode= this.toAddressGroup.value.postalcode;
          this.addressBackend.state  = this.toAddressGroup.value.state;
          this.fedexService.createAddress(this.addressBackend,customerId).subscribe(data =>{
            console.log("Customer To Address Added to database success");
            console.log("customer To Address data -> "+data);
        
            this._snackbar.open("Customer To Added to database success", null ,{
              duration: 3 * 1000
            });
            this._router.navigate(['/delivery/options']);
          },
          error => {
            console.log("ERROR:: Customer Added to database failed");
          });
  
        }
      
      },
      error => {
        console.log("ERROR:: Customer Added to database failed");
      });
   
    }
  
  }

  clearStorage() {
    this.toAddressGroup.reset();
    this.toAddressGroup.patchValue({addressType: 'Work'});
    this.isSavedInStorage = false;
  }

}

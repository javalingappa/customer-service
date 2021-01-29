import { Component, OnInit } from '@angular/core';

import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Shipping } from 'src/app/model/shipping.model';
import { FedexService } from 'src/app/address/services/fedex.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'options-page',
  templateUrl: './options.component.html',
  styleUrls: ['./options.component.scss']
})

export class OptionsComponent implements OnInit {

  shippingServiceConfirmGroup: FormGroup;
  shipping: Shipping;
  constructor(private fedexService: FedexService, private _formBuilder: FormBuilder, 
    private router: Router, private _httpClient: HttpClient,private _snackbar: MatSnackBar) { 
  }

  ngOnInit(): void {
    this.shippingServiceConfirmGroup = this._formBuilder.group({
      services: ['', Validators.required],
      options: ['', Validators.required],
    });
  }

  shippingServiceConfirmSubmit() {
    if(this.shippingServiceConfirmGroup.valid) {
      const customerId = localStorage.getItem('customerId');
      const shippingDetails = this.shippingServiceConfirmGroup.value;
      if(customerId) {
        this.shipping = new Shipping();
        this.shipping.option = shippingDetails.options;
        this.shipping.service =  shippingDetails.services;
        this.fedexService.createShippingService(this.shipping,customerId).subscribe(data => {
          console.log("Customer Shipping Service Added to database success");
          console.log("customer data -> " + data);
          this._snackbar.open("Customer Added to database success", null, {
            duration: 3 * 1000
          });
        },
        error => {
            console.log("ERROR:: Customer Added to database failed");
          });
      }
      this.router.navigate(['./delivery/order-confirmation']);
      localStorage.removeItem('customerId');
    }
  }

}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {environment} from '../../../environments/environment'

@Injectable({
    providedIn: 'root'
})
export class FedexService {

    constructor(private httpService: HttpClient) { }

    createCustomer(customer): Observable<any> {
        return this.httpService.post<any>(environment.baseApiUrl+'customer',customer);
    }

    createAddress(address,customerId): Observable<any> {
        return this.httpService.post<any>(environment.baseApiUrl+'customer/address?customerId='+customerId,address);
    }

    createShippingService(shipping,customerId): Observable<any> {
        return this.httpService.post<any>(environment.baseApiUrl+'customer/shipping/service?customerId='+customerId,shipping);
    }




}
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cust } from '../customers/cust';

@Injectable({
  providedIn: 'root'
})
export class CustomerserviceService {

  constructor(private _http: HttpClient) { }

  getdata() {
    return this._http.get('http://localhost:5020/api/v1/customers/all_customers')
  }
  createcustomer(customer: Cust): Observable<any> {
    console.log(customer);
    
    return this._http.post('http://localhost:5020/api/v1/customers', customer)
  }

  getById(customerNumber:any):Observable<any>{
    return this._http.get<Cust>(`http://localhost:5020/api/v1/v1/customers/${customerNumber}`);
  }

  updatecustomer(
    customerNumber: number,
    customerName: string,
    contactLastName: string,
    contactFirstName: string,
    phone: string,
    addressLine1: string,
    addressLine2: string,
    city: string,
    state: string,
    postalCode: string,
    country: string,
    salesRepEmployeeNumber: string,
    creditLimit: number): Observable<any> {
    const url = `http://localhost:5020/api/v1/customers/update/${customerNumber}`;
    const body = {
  
      customerName: customerName,
      contactLastName: contactLastName,
      contactFirstName: contactFirstName,
      phone: phone,
      addressLine1: addressLine1,
      addressLine2: addressLine2,
      city: city,
      state: state,
      postalCode: postalCode,
      country: country,
      salesRepEmployeeNumber: salesRepEmployeeNumber,
      creditLimit: creditLimit
    };
console.log(body);

    return this._http.put(url, body)
  }
}

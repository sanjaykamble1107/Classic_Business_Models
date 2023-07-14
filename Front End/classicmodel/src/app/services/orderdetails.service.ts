import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OrdDetails } from '../orderdetails/orddetails';

@Injectable({
  providedIn: 'root'
})
export class OrderdetailsService {

  constructor(private _http: HttpClient) { }
  getdata() {
    return this._http.get('http://localhost:5020/api/v1/orderdetails/all_order_details');
  }
  createorderdetails(orderdetails: OrdDetails): Observable<any> {
    console.log(orderdetails.orderNumber);
    return this._http.post('http://localhost:5020/api/v1/orderdetails/', orderdetails);
  }

  getByOrderNumber(orderNumber: any): Observable<any> {
    return this._http.get<OrdDetails>(`http://localhost:5020/api/v1/orderdetails/${orderNumber}`)
  }


  updateorderdetails(
    orderNumber: number,
    productCode: string,
    quantityOrdered: number,
    priceEach: number,
    orderLineNumber: number,

  ): Observable<any> {
    const url = `http://localhost:5020/api/v1/orderdetails/update/${orderNumber}/${productCode}`;
    const body = {
      orderNumber: orderNumber,
      productCode: productCode,
      quantityOrdered: quantityOrdered,
      priceEach: priceEach,
      orderLineNumber: orderLineNumber,
    };
    console.log(body);

    return this._http.put(url, body)
  }
}

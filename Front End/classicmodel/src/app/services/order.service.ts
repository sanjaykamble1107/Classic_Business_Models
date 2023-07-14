import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ord } from '../orders/ord';

@Injectable({
  providedIn: 'root'
})
export class OrderService {


  constructor(private _http: HttpClient) { }

  getdata() {
    return this._http.get('http://localhost:5020/api/v1/orders/all_orders');
  }

  createOrder(order: Ord): Observable<any> {
    console.log(order);

    return this._http.post('http://localhost:5020/api/v1/orders/', order)
  }
  
  getByOrderNumber(orderNumber:any):Observable<any>{
    return this._http.get<Ord>(`http://localhost:5020/api/v1/orders/order_number/${orderNumber}`);
  }


  updateorder(
    orderNumber: number,
    orderDate: string,
    requiredDate: string,
    shippedDate: string,
    status: string,
    comments: string,
    customerNumber: number): Observable<any> {

    const url = `http://localhost:5020/api/v1/orders/update/${orderNumber}`;
    const body = {
      orderNumber:orderNumber,
      orderDate: orderDate,
      requiredDate: requiredDate,
      shippedDate: shippedDate,
      status: status,
      comments: comments,
      customerNumber: customerNumber
    };
    return this._http.put(url, body)
  }

}

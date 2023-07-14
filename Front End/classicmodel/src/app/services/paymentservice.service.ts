import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pay } from '../payments/payment';

@Injectable({
  providedIn: 'root'
})
export class PaymentserviceService {

  constructor(private http: HttpClient) { }

  createPayment(payment:Pay):Observable<any>{
    return this.http.post('http://localhost:5020/api/v1/payments/save',payment);
  }

  getAllPaymentDetailsByPaymentDate(paymentDate: string) {
    return this.http.get<Pay[]>(`http://localhost:5020/api/v1/payments/paymentDate/${paymentDate}`);
  }
 
  getAllPaymentDetailsForSpecificCustomer(customerNumber: number) {
    return this.http.get<Pay[]>(`http://localhost:5020/api/v1/payments/customerNumber/${customerNumber}`);
  }
 
  getAllPaymentDetailsByCheckNumber(checkNumber: string) {
    console.log("in service"+checkNumber);
   
    return this.http.get<Pay>(`http://localhost:5020/api/v1/payments/checkNumber/${checkNumber}`);
  }
 
  updatePaymentByCheckNumber(checkNumber: string, payment: Pay): Observable<any> {
    return this.http.put(`http://localhost:5020/api/v1/payments/${checkNumber}`, payment);
   
  }


}

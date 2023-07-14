import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Off } from '../office/off';

@Injectable({
  providedIn: 'root'
})
export class OfficeService {

  constructor(private _http: HttpClient) { }

  getdata() {
    // console.log("Akshayyyyy");
    return this._http.get('http://localhost:5020/api/v1/office/all_office_details');

  }
  createOffice(office: Off): Observable<Object> {
    return this._http.post('http://localhost:5020/api/v1/office', office)
  }

  getbyId(officeCode:any):Observable<any>{

    return this._http.get<Off>(`http://localhost:5020/api/v1/office/${officeCode}`);
  }

  updateOffice(
    officeCode: number,
    city: string,
    phone: number,
    addressLine1: string,
    addressLine2: string,
    state: string,
    country: string,
    postalCode: number,
    territory: string,
  ): Observable<any> {

    const url = `http://localhost:5020/api/v1/office/update/${officeCode}`;
    const body = {
      officeCode: officeCode,
      city: city,
      phone: phone,
      addressLine1: addressLine1,
      addressLine2: addressLine2,
      state: state,
      country: country,
      postalCode: postalCode,
      territory: territory,
    };

    return this._http.put(url, body)
  }
}

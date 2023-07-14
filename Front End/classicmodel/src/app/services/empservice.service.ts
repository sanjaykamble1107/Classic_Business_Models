import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Emp } from '../employees/emp';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class EmpserviceService {
 
  constructor(private _http:HttpClient) { }

  getdata(){
    return this._http.get('http://localhost:5020/api/v1/v1/employee/all_employee_details');
  }

  createEmployee(employee:Emp):Observable<any>{
    console.log(employee);
    return this._http.post('http://localhost:5020/api/v1/employee',employee)
  }
  
  updateEmployee(employeeNumber:number, lastName: string, firstName: string, extension: string, email: string,  
    officeCode: number, reportsTo: number, jobTitle: string): 
    Observable<any> {
  
   
  console.log(lastName);
    const url = `http://localhost:5020/api/v1/employee/${employeeNumber}`;
    const body = {
      employeeNumber: employeeNumber,
      lastName: lastName,
      firstName: firstName,
      extension: extension,
      email: email,
      officeCode: officeCode,
      reportsTo: reportsTo,
      jobTitle: jobTitle
    };

    console.log(body.firstName);
    console.log("in update");
  
    return this._http.put(url, body);
  }
searchcity(city:any){
  return this._http.get(`http://localhost:5020/api/v1/v1/employee/all_employee_details_city/${city}`)
}

// }
  // updateEmployee(employeeNumber:any,getdata:any){
  // }
  // getbyId(employeeNumber: number): Observable<any> {
  //   const url = `http://localhost:5020/api/v1/v1/employee/${employeeNumber}`;
  //   return this._http.get(url);
  // }

  // public getCurrentData(employeeNumber:number): Observable<Emp> {

    //console.log(this._http.get<Emp>(`http://localhost:5020/api/v1/v1/employee/1002`));

     // this._http.get<Emp>(`http://localhost:5020/api/v1/v1/employee/1002`)

    // return this._http.get<Emp>(`http://localhost:5020/api/v1/v1/employee/${employeeNumber}`);

    // //console.log("Service"+employeeNumber);
    // console.log("response of get call----------------"+this._http.get(`http://localhost:5020/api/v1/v1/employee/${employeeNumber}`));
    // return  this._http.get<any>(`http://localhost:5020/api/v1/v1/employee/${employeeNumber}`);
  

  

//}
  getbyId(employeeNumber:any):Observable<any>{
   //let url:'http://localhost:5020/api/v1/v1/employee/{employeeNumber}';

    
    return this._http.get<Emp>(`http://localhost:5020/api/v1/v1/employee/${employeeNumber}`);
  }
  // getbyId(employeeNumber: number) {
  //   return this._http.get(`http://localhost:5020/api/v1/v1/employee/${employeeNumber}`);
  // }
// }
}
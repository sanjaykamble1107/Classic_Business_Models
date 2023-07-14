import { HttpClient, HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient) { }

  baseUrl:string="http://localhost:5020/auth"
  registerUser(username:string,password:string):Observable<any>
  {
    const body={
      name:username,
      password:password
    }
      return this.http.post(`${this.baseUrl}/register`,body,{responseType:'text' as 'json'});
  }

  onLogin(name:string,password:string):Observable<any>
  {
    const body={
      name:name,
      password:password
    }
      return this.http.post(`${this.baseUrl}/login`,body)
  }
}

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('token');
    if (token) {
      // Clone the request and add the token to the headers
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }
    return next.handle(request);
  }


}

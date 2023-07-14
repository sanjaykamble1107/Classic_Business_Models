import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username:string ="";
  password:string ="";
  constructor(private authService:AuthService,private router: Router){}
  ngOnInit(): void {
   
  }
 
  registerUser()
  {
      if(this.username==""||this.password=="")
      {
        alert("Input the values");
      }

      console.log(this.username);
      console.log(this.password);
     
    let rsp = this.authService.registerUser(this.username,this.password);

    rsp.subscribe(
      (data) => {
        console.log(data);
        alert("Registered successfully. Please login.");
      },
      (error) => {
       
          alert("User already exists,Please Login"); // Handle the specific error scenario
       
      }
    );
  }
  onLogin()
  {
    if(this.username==""||this.password=="")
      {
        alert("Input the values");
      }

      let rsp = this.authService.onLogin(this.username,this.password);

      rsp.subscribe(data=>{
        console.log(data);

        const token = data.jwtToken; // Assuming the JWT token is returned as 'token'
        // Save the token in local storage or session storage
        localStorage.setItem('token', token);

         alert("Login Successful")
        this.router.navigate(['/homepage']);
       
      })
   
   
  }


}

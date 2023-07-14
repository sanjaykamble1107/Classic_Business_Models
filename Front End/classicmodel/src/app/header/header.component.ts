import { Component } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

//  isVisible:boolean=false;
constructor(private router:Router){}
  logout()
  {
    localStorage.clear();
    alert("User Logout")

    this.router.navigate(['/homepage'])
    
    
    
  }

}

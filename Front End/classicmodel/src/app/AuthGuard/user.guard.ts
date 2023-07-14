import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';

@Injectable({

  providedIn: 'root'

})

export class UserGuard implements CanActivate {



  constructor(private router: Router) { }



  canActivate(

    route: ActivatedRouteSnapshot,

    state: RouterStateSnapshot

  ): boolean {

    const tkn = localStorage.getItem("token");

    if(tkn===null)
    {
      this.router.navigateByUrl('/login');
      return false;
    }
    else
    {
      return true;
    }
   

  }



}


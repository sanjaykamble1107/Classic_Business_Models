import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Emp } from '../employees/emp';
import { EmpserviceService } from '../services/empservice.service';
import { Observable } from 'rxjs';
import { FormControl, FormGroup } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {
  employeeNumber: number = 0;
  lastName: string = '';
  firstName: string = '';
  extension: string = '';
  email: string = '';
  officeCode: number = 0;
  reportsTo: number = 0;
  jobTitle: string = '';
  // employee: any = {};


 
  constructor(private employeeService: EmpserviceService, private routerr: ActivatedRoute, private router: Router) { }
  ngOnInit(): void {
    
    this.employeeNumber = this.routerr.snapshot.params['employeeNumber'];
    let rsp = this.employeeService.getbyId(this.employeeNumber);

    
    rsp.subscribe(data=>{
      //console.log(data.lastName);
    
      this.lastName = data.lastName;
      this.firstName=data.firstName;
      this.extension=data.extension;
      this.email=data.email;
      this.officeCode=data.officeCode;
      this.reportsTo=data.reportsTo;
      this.jobTitle=data.jobTitle;
    })


  }

  updateEmployee() {
    this.employeeService.updateEmployee(

      this.employeeNumber,
      this.lastName,
      this.firstName,
      this.extension,
      this.email,
      this.officeCode,
      this.reportsTo,
      this.jobTitle

    ).subscribe(
      response => {
        console.log('Employee updated successfully');
        const message = response.message;
        alert(message);
        this.goToEmployeeList();
        // Additional logic or redirection after updating the employee
      },
      error => {
        console.log('Error updating employee:', error);
      }
    );
  }
  onSubmit() {

    //  console.log(this.office);

    this.goToEmployeeList();

  }
  goToEmployeeList() {
    this.router.navigate(['/employees']);
  }



}




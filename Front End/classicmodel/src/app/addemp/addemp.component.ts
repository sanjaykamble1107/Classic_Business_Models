import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Emp } from '../employees/emp';
import { EmpserviceService } from '../services/empservice.service';

@Component({
  selector: 'app-addemp',
  templateUrl: './addemp.component.html',
  styleUrls: ['./addemp.component.css']
})
export class AddempComponent {
  employee: Emp = new Emp();     //creating state object
  constructor(private empserviceService: EmpserviceService, private router: Router) { }
  e: Emp = new Emp();
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  saveEmployee() {
    // this.e = this.employee;
    //console.log(this.e);
    this.empserviceService.createEmployee(this.employee).subscribe(

      data => {
        const msg = data.message;
        //console.log(msg);
        alert(msg);
        this.goToEmployeeList();
      },
      error => {
        alert(error.error.errorMessage);
        console.error('Error creating employee:', error.errorMessage);
      }
    );
  }

  // 

  formSubmitted = false;
  onSubmit() {

    console.log(this.employee);
    if (
      !this.employee.lastName ||
      !this.employee.firstName ||
      !this.employee.extension ||
      !this.employee.email ||
      !this.employee.officeCode ||
      !this.employee.reportsTo ||
      !this.employee.jobTitle
    ) {
      // Set the formSubmitted flag to true
      this.formSubmitted = true;
      return; // Prevent form submission
    }
    this.saveEmployee();

    // this.e = this.employee;
    //console.log(this.e);

  }
  goToEmployeeList() {
    this.router.navigate(['/employees']);
  }

}


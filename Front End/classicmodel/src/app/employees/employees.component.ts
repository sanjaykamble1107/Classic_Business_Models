import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EmpserviceService } from '../services/empservice.service';
import { Emp } from './emp';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent {
  searchId: number | undefined;
  searchOfficeCode: number | undefined;
  title = 'apidata';
  newdata: any;
  oneEmp: Emp | undefined;
  onecity: Emp | undefined;
  emplist: Emp[] = []
  emp: Emp[] = [];
  filterCity: Emp[] = [];
  empCity: Emp[] = [];
  searchCity: string | undefined;
  inputcity: string | undefined;
  filteredEmpList: Emp[] = [];
  constructor(private empservice: EmpserviceService, private router: ActivatedRoute) { }

  ngOnInit() {
    this.empservice.getdata().subscribe((res: any) => {
      this.emplist = [...res];
      this.emp = this.emplist;

    })
    //console.log(this.newdata);
  }




  searchById() {
    for (let oneEmp of this.emplist) {
      if (this.searchId == oneEmp.employeeNumber) {
        this.emp = [];
        console.log(oneEmp);
        this.emp.push(oneEmp);
        this.oneEmp = oneEmp; // Assign the matched employee to the oneEmp property
        break; // Exit the loop since the employee is found

      } else {
        this.oneEmp = undefined; // Reset oneEmp if the employee is not found
      }

    }
    if(!this.searchId){
      this.emp=this.emplist;
    }

  }

  searchByOfficeCode() {
    this.filteredEmpList = [];

    for (let oneEmp of this.emplist) {
      if (this.searchOfficeCode == oneEmp.officeCode) {
        this.filteredEmpList.push(oneEmp)
        this.emp = [];
        console.log(oneEmp);
        // this.emp.push(oneEmp);
        this.oneEmp = oneEmp; // Assign the matched employee to the oneEmp property
        // break; // Exit the loop since the employee is found
      }
      else {
        this.oneEmp = undefined; // Reset oneEmp if the employee is not found
      }
    }
    if(!this.searchOfficeCode){
      this.emp=this.emplist
    }
  }
  searchByCity() {
    this.filterCity = [];
    this.empservice.searchcity(this.inputcity).subscribe((res: any) => {
      this.filterCity = res;
      console.log(this.filterCity);
    });
  }

}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerserviceService } from 'src/app/services/customerservice.service';

@Component({
  selector: 'app-updatecustomer',
  templateUrl: './updatecustomer.component.html',
  styleUrls: ['./updatecustomer.component.css']
})
export class UpdatecustomerComponent implements OnInit {
  customerNumber = 0;
  customerName = " ";
  contactLastName = " ";
  contactFirstName = " ";
  phone = " ";
  addressLine1 = " ";
  addressLine2 = " ";
  city = " ";
  state = " ";
  postalCode = " ";
  country = " ";
  salesRepEmployeeNumber = "";
  creditLimit = 0;

  constructor(private customerservice: CustomerserviceService, private routerr: ActivatedRoute, private router: Router) { }
  ngOnInit(): void {
    this.customerNumber = this.routerr.snapshot.params['customerNumber'];

    let resp=this.customerservice.getById(this.customerNumber);

    resp.subscribe(data=>{
      this.customerNumber=data.customerNumber;
      this.customerName=data.customerName;
      this.contactLastName=data.contactLastName;
      this.contactFirstName=data.contactFirstName;
      this.phone=data.phone;
      this.addressLine1=data.addressLine1;
      this.addressLine2=data.addressLine2;
      this.city=data.city;
      this.state=data.state;
      this.postalCode=data.postalCode;
      this.country=data.country
      this.salesRepEmployeeNumber=data.salesRepEmployeeNumber;
      this.creditLimit=data.creditLimit
    })
    
  }
  updateCustomer() {
      this.customerservice.updatecustomer(

      this.customerNumber,
      this.customerName,
      this.contactLastName,
      this.contactFirstName,
      this.phone,
      this.addressLine1,
      this.addressLine2,
      this.city,
      this.state,
      this.postalCode,
      this.country,
      this.salesRepEmployeeNumber,
      this.creditLimit
    ).subscribe(
      response => {
        // console.log('Customer updated successfully');
        const message = response.message;
        alert(message); 
        this.goToCustomerList();    
        // this.router.navigate(['/customers']);

      },
      error => {
        console.log('Error updating customer:', error);
      }
    );
  }
  onSubmit() {
    this.updateCustomer();
    // this.router.navigate(['/customers']);
    // this.goToCustomerList();
  }
  goToCustomerList() {
    this.router.navigate(['/customers']);
  }
}

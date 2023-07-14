import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Cust } from '../customers/cust';
import { CustomerserviceService } from '../services/customerservice.service';

@Component({
  selector: 'app-addcust',
  templateUrl: './addcust.component.html',
  styleUrls: ['./addcust.component.css']
})
export class AddcustComponent {
  customer: Cust = new Cust();

  constructor(private custservice: CustomerserviceService, private router: Router) { }
  
  /**
   * Saves the customer by calling the customer service createcustomer() method.
   * Displays an alert message with the response data and navigates to the customer list page.
   */
  saveCustomer() {
    this.custservice.createcustomer(this.customer).subscribe(
      data => {
        const message = data.message;
        alert(message);
        this.goToCustomerList();
      },
      error => {
        console.log('Error creating customer', error);
      }
    );
  }

  formSubmitted = false;

  /**
   * Handles the form submission.
   * If the form is valid, calls the saveCustomer() method.
   * If the form is invalid, sets the formSubmitted flag to true.
   */
  onSubmit() {
    console.log(this.customer);
    if (
      !this.customer.customerName ||
      !this.customer.contactLastName ||
      !this.customer.contactFirstName ||
      !this.customer.phone ||
      !this.customer.addressLine1 ||
      !this.customer.addressLine2 ||
      !this.customer.city ||
      !this.customer.state ||
      !this.customer.country ||
      !this.customer.salesRepEmployeeNumber ||
      !this.customer.creditLimit
    ) {
      this.formSubmitted = true;
      return;
    }
    this.saveCustomer();
  }

  /**
   * Navigates to the customer list page.
   */
  goToCustomerList() {
    console.log("cust here");
    this.router.navigate(['/customers']);
  }
}

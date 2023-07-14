import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrdDetails } from '../orderdetails/orddetails';
import { OrderdetailsService } from '../services/orderdetails.service';

@Component({
  selector: 'app-addorderdetails',
  templateUrl: './addorderdetails.component.html',
  styleUrls: ['./addorderdetails.component.css']
})
export class AddorderdetailsComponent {

  orderDetails: OrdDetails = new OrdDetails;

  constructor(private orderdetailsservice: OrderdetailsService, private roter: ActivatedRoute, private router: Router) { }
  saveOrderDetails() {
    console.log(this.orderDetails.orderNumber);

    this.orderdetailsservice.createorderdetails(this.orderDetails).subscribe(
      data => {
        const message = data.message;
        alert(message);
        this.goToOrderDetailsList();
      },
      error => {
        console.log('Error creationg orderdetails', error);


      }
    );
  }
  formSubmitted = false;
  onSubmit() {
    // this.saveOrderDetails();

    if (
      !this.orderDetails.orderNumber ||
      !this.orderDetails.orderLineNumber ||
      !this.orderDetails.priceEach ||
      !this.orderDetails.quantityOrdered ||
      !this.orderDetails.productCode
    ) {
      this.formSubmitted = true;
      return;
    }


    this.saveOrderDetails();
    // this.router.navigate(['/orderdetails']);
  }
    // this.goToOrderDetailsList();
  

  goToOrderDetailsList() {
    this.router.navigate(['/orderdetails']);

  }
}

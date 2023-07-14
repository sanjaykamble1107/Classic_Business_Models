import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Ord } from '../orders/ord';
import { OrderService } from '../services/order.service';

@Component({
  selector: 'app-addorder',
  templateUrl: './addorder.component.html',
  styleUrls: ['./addorder.component.css']
})
export class AddorderComponent {

  order: Ord = new Ord();

  constructor(private orderservice: OrderService, private router: Router) { }

  saveOrder() {
    this.orderservice.createOrder(this.order).subscribe(
      data => {
        console.log(data);
        const message = data.message;
        alert(message);
        this.goToOrderList();
      },
      error => {
        console.error('Error Creating Order', error);
      }
    );
  }

  formSubmitted = false;

  onSubmit() {
    console.log(this.order);

    if (
      !this.order.customerNumber ||
      !this.order.comments ||
      !this.order.orderDate ||
      !this.order.requiredDate ||
      !this.order.shippedDate ||
      !this.order.status
    ) {
      this.formSubmitted = true;
      return;
    }

    this.saveOrder();
  }

  goToOrderList() {
    console.log("-------");
    this.router.navigate(['/orders']);
  }
}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-updateorder',
  templateUrl: './updateorder.component.html',
  styleUrls: ['./updateorder.component.css']
})
export class UpdateorderComponent implements OnInit {
  orderNumber = 0;
  orderDate = "";
  requiredDate = "";
  shippedDate = "";
  status = "";
  comments = "";
  customerNumber = 0;

  constructor(private orderservice: OrderService, private routerr: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.orderNumber = this.routerr.snapshot.params['orderNumber'];
  let resp=this.orderservice.getByOrderNumber(this.orderNumber);

  resp.subscribe(data =>{
    this.orderNumber=data.orderNumber;
    this.orderDate=data.orderDate;
    this.requiredDate=data.requiredDate;
    this.shippedDate=data.shippedDate;
    this.status=data.status;
    this.comments=data.comments;
    this.customerNumber=data.customerNumber
  })
    
  }

  updateOrder() {
    this.orderservice.updateorder(
      this.orderNumber,
      this.orderDate,
      this.requiredDate,
      this.shippedDate,
      this.status,
      this.comments,
      this.customerNumber,
    ).subscribe(
      respose => {
        console.log('Order Updated Succesfully');
        alert("Order Updated Succesfully")
        this.goToOrderList();
      },
      error => {
        alert(" " + error.error.message)
        console.log('Error in updating order', error);

      }
    );
  }
  onSubmit() {
    this.updateOrder();

  }
  goToOrderList() {
    this.router.navigate(['/orders']);
  }

}

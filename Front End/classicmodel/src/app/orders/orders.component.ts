import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrderService } from '../services/order.service';
import { Ord } from './ord';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent {

  orderlist: Ord[] = [];
  ord: Ord[] = [];
  filterCustomerList: Ord[] = [];
  customerlist: Ord[] = [];

  onecustomer: Ord | undefined;
  searching: number | undefined;
  selectedCriteria: string = 'hello';
  constructor(private orderservice: OrderService, private roter: ActivatedRoute) { }
  ngOnInit() {
    this.orderservice.getdata().subscribe((res: any) => {
      this.orderlist = [...res];
      this.ord = this.orderlist;
      this.customerlist = [...res];
    })

  }

  search() {
    console.log(this.selectedCriteria);
    if (Number(this.searching) == 0) {
      this.ngOnInit();
    }
    else if (this.selectedCriteria === "customerNumber") {
      console.log(this.selectedCriteria);

      this.getOrderByCustomerNumber();
    }
    else if (this.selectedCriteria === "orderNumber") {
      this.getOrderByOrderNumber();
    }
  }

  getOrderByCustomerNumber() {
    this.filterCustomerList = [];
    console.log(this.filterCustomerList);

    for (let onecustomer of this.customerlist) {
      if (this.searching == onecustomer.customerNumber) {
        this.filterCustomerList.push(onecustomer)
        this.ord = [];
        console.log(this.filterCustomerList);
        // this.ord.push(onecustomer);
        this.onecustomer = onecustomer;
        //  break;
      } else {
        this.onecustomer = undefined;
      }
    }
  }
  getOrderByOrderNumber() {
    this.filterCustomerList = [];
    console.log(this.filterCustomerList);

    for (let onecustomer of this.customerlist) {
      if (this.searching == onecustomer.orderNumber) {
        this.filterCustomerList.push(onecustomer)
        this.ord = [];
        console.log(this.filterCustomerList);
     //   this.ord.push(onecustomer);
        this.onecustomer = onecustomer;
        //  break;
      } else {
        this.onecustomer = undefined;
      }
    }
  }

}

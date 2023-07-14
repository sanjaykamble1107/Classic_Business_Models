import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderdetailsService } from '../services/orderdetails.service';
import { OrdDetails } from './orddetails';

@Component({
  selector: 'app-orderdetails',
  templateUrl: './orderdetails.component.html',
  styleUrls: ['./orderdetails.component.css']
})
export class OrderdetailsComponent {
  orderdetailslist: OrdDetails[] = [];
  orderdetlist: OrdDetails[] = [];
  selectedCriteria: string | any;
  searchorder: number | undefined;
  order: OrdDetails[] = [];
  oneOrder: OrdDetails | undefined;
  filterOrderList: OrdDetails[] = [];



  constructor(private orderdetailsservice: OrderdetailsService) { }
  ngOnInit() {
    this.orderdetailsservice.getdata().subscribe((res: any) => {
      this.orderdetailslist = [...res];
      this.orderdetlist = this.orderdetailslist;
    })
  }

  // search() {
  //   if (this.selectedCriteria = "orderNumber") {
  //     this.searchOrderNumber();
  //   }
  // }
  searchOrderNumber() {
    this.filterOrderList = [];
    for (let oneorder of this.orderdetlist) {
      if (this.searchorder == oneorder.orderNumber) {
        this.filterOrderList.push(oneorder)
        this.order = [];
        console.log(oneorder);
        this.order.push(oneorder);
        this.oneOrder = oneorder;
        //break;
      } else {
        this.oneOrder = undefined;
      }
    }
  }






}

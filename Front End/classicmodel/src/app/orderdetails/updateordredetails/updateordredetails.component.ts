import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderdetailsService } from 'src/app/services/orderdetails.service';

@Component({
  selector: 'app-updateordredetails',
  templateUrl: './updateordredetails.component.html',
  styleUrls: ['./updateordredetails.component.css']
})
export class UpdateordredetailsComponent implements OnInit {
  orderNumber = 0;
  productCode = " ";
  quantityOrdered= 0;
  priceEach=0;
  orderLineNumber= 0;

 
  constructor(private orderdetailservice:OrderdetailsService,private routerr:ActivatedRoute,private router:Router){}
  ngOnInit(): void {
    this.orderNumber=this.routerr.snapshot.params['orderNumber'];
    this.productCode=this.routerr.snapshot.params['productCode'];
    
    // let resp=this.orderdetailservice.getByOrderNumber(this.orderNumber);

    // resp.subscribe(data =>{
    //   // console.log(this.priceEach);
    //   this.orderNumber=data.orderNumber;
    //   this.productCode=data.productCode;
    //   this.quantityOrdered=data.quantityOrdered;
    //   this.priceEach=data.priceEach;
    //   this.orderLineNumber=data.orderLineNumber

    // })
  }

  updateOrderDetails(){
    this.orderdetailservice.updateorderdetails(
      
    this.orderNumber,
    this.productCode,
    this.quantityOrdered,
    this.priceEach,
    this.orderLineNumber,
    ).subscribe(
      response => {
        // console.log('Order Details Updated Succesfully');
        // alert("Order Details Updated Successfully")
        const message =response.message;
        alert(message);
        this.goToOrderDetailsList();
        
      },
      error => {
        alert(" "+error.error.message)
        console.log('Error In Updating Order',error);
        
      }
      
    );
  }
  onSubmit(){
    this.updateOrderDetails();
    // this.goToOrderDetailsList();
  }
goToOrderDetailsList(){
  this.router.navigate(['/orderdetails']);
}
}

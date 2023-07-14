import { Component } from '@angular/core';
import { PaymentserviceService } from '../services/paymentservice.service';
import { Pay } from './payment';

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.css']
})
export class PaymentsComponent {
  customerNumber: number;
  checkNumber: string;
  paymentDate: string; // Add the paymentDate property as a string
  selectedCriteria: string | any;
  searchValue: string | any;
  paymentDetails: Pay[] = [];
  pay: Pay[] = [];
 
  onePay: Pay | undefined;

  constructor(private paymentsService: PaymentserviceService) {
    this.customerNumber = 0;
    this.checkNumber = "";
    this.paymentDate = "";
  }

  ngOnInit() {

  }
  search() {
    if (this.selectedCriteria === 'paymentDate') {
      console.log(this.selectedCriteria);
       this.getAllPaymentDetailsByPaymentDate();
    }
    else if (this.selectedCriteria === 'customerNumber') {
     this.getAllPaymentDetailsForSpecificCustomer();
    }
    else if (this.selectedCriteria === 'checkNumber') {
      console.log(this.checkNumber);
      this.getAllPaymentDetailsByCheckNumber();
    }

  }

  getAllPaymentDetailsByPaymentDate() {
    console.log(this.paymentDate);
    
    this.paymentsService.getAllPaymentDetailsByPaymentDate(this.searchValue)
      .subscribe((data: Pay[]) => {
        this.paymentDetails = [...data];
        
      });

  }

  getAllPaymentDetailsForSpecificCustomer() {
    this.paymentsService.getAllPaymentDetailsForSpecificCustomer(this.searchValue)
    .subscribe((data: Pay[]) => {
      this.paymentDetails = [...data];
    });
    }
 
  getAllPaymentDetailsByCheckNumber() {
    console.log(this.searchValue);
   
    this.paymentsService.getAllPaymentDetailsByCheckNumber(this.searchValue)
    .subscribe((data: Pay) => {
     this.paymentDetails.push(data);
      console.log(this.paymentDetails);
     
     
    });

  }


}

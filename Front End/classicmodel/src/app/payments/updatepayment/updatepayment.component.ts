import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { PaymentserviceService } from 'src/app/services/paymentservice.service';
import { Pay } from '../payment';

@Component({
  selector: 'app-updatepayment',
  templateUrl: './updatepayment.component.html',
  styleUrls: ['./updatepayment.component.css']
})
export class UpdatepaymentComponent {
  payment: Pay = {
    checkNumber: '',
    paymentDate: '',
    amount: 0,
    customerNumber: 0
  };

  constructor(private paymentService: PaymentserviceService,private router:Router) {}

  onSubmit() {
    this.paymentService.updatePaymentByCheckNumber(this.payment.checkNumber, this.payment).subscribe(
      response => {
        console.log(response);
        // Handle the response, display a success message, or perform any necessary actions.
        this.router.navigate(['/payments']);
      },
      error => {
        console.log(error);
        // Handle the error, display an error message, or perform any necessary actions.
        if (error instanceof HttpErrorResponse) {
          console.log(error.error.message);
      }
    }
    );
  }


}

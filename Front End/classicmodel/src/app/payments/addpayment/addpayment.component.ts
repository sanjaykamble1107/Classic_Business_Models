import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { PaymentserviceService } from 'src/app/services/paymentservice.service';
import { Pay } from '../payment';

@Component({
  selector: 'app-addpayment',
  templateUrl: './addpayment.component.html',
  styleUrls: ['./addpayment.component.css']
})
export class AddpaymentComponent {
  payment :Pay=new Pay();
  constructor(private paymentservice:PaymentserviceService,private router:Router){}

savePayments(){
this.paymentservice.createPayment(this.payment).subscribe({
next:(data)=>{
 console.log(data);
 this.goToPaymentList();
},

error: (error) => {

 console.error('Error creating payment:');

}
});
}
onSubmit() {
console.log(this.payment);
this.savePayments();
this.router.navigate(['/payments']);
}

goToPaymentList() {



}


}

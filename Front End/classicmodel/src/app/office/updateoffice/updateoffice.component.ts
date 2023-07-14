import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OfficeService } from 'src/app/services/office.service';

@Component({
  selector: 'app-updateoffice',
  templateUrl: './updateoffice.component.html',
  styleUrls: ['./updateoffice.component.css']
})
export class UpdateofficeComponent {
  officeCode: number = 0;
  city: string = '';
  phone: number = 0;
  addressLine1: string = '';
  addressLine2: string = '';
  state: string = '';
  country: string = '';
  postalCode: number = 0;
  territory: string = '';


  constructor(private officeService: OfficeService, private routerr: ActivatedRoute,private router:Router) { }
  ngOnInit(): void {

    this.officeCode = this.routerr.snapshot.params['officeCode'];
    let resp= this.officeService.getbyId(this.officeCode);
    
    resp.subscribe(data=>{
      console.log(this.city);
      
      this.officeCode=data.officeCode;
      this.city=data.city;
      this.phone=data.phone;
      this.addressLine1=data.addressLine1;
      this.addressLine2=data.addressLine2;
      this.state=data.state;
      this.country=data.country;
      this.postalCode=data.postalCode;
      this.territory=data.territory
    })
  }

  updateOffice() {
    this.officeService.updateOffice(

      this.officeCode,
      this.city,
      this.phone,
      this.addressLine1,
      this.addressLine2,
      this.state,
      this.country,
      this.postalCode,
      this.territory

    ).subscribe(
      response => {
        //console.log('Employee updated successfully');
        const message = response.message;
        // console.log(message);
        alert(message);
        // alert("Office Updated Successfully.");

        this.goToOfficeList();
        // Additional logic or redirection after updating the employee
      },
      error => {
        console.log('Error updating employee:', error);
      }
    );
  }
  onSubmit() {
    
   console.log(this.updateOffice);

    this.updateOffice();
   
    
     }
  goToOfficeList() {
    this.router.navigate(['/office']);
  }
}

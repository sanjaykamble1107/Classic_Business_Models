import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Off } from '../office/off';
import { OfficeService } from '../services/office.service';

@Component({
  selector: 'app-addoffice',
  templateUrl: './addoffice.component.html',
  styleUrls: ['./addoffice.component.css']
})
export class AddofficeComponent {

  office: Off = new Off();     //creating state object
  constructor(private officeservice: OfficeService, private router: Router) { }
  // e:Off = new Off();
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  saveOffice() {
    // this.e = this.employee;
    //console.log(this.e);
    this.officeservice.createOffice(this.office).subscribe({
      next: data => {
        console.log(data);
        this.goToOfficeList();
      },
      error: error => {
        console.error('Error creating employee:', error);
      }
    });
  }

  formSubmitted = false;
  onSubmit() {

    console.log(this.office);
    if (

      !this.office.city ||
      !this.office.phone ||
      !this.office.addressLine1 ||
      !this.office.addressLine2 ||
      !this.office.state ||
      !this.office.country ||
      !this.office.postalCode ||
      !this.office.territory
    ) {
      // Set the formSubmitted flag to true
      this.formSubmitted = true;
      return; // Prevent form submission
    }
    this.saveOffice();

  }
  goToOfficeList() {
    this.router.navigate(['/office']);
  }

}

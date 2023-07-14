import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CustomerserviceService } from '../services/customerservice.service';
import { Cust } from './cust';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent {
  home: string | undefined;
  customers!: any[]; // Assuming you have an array of customers
  custlist: Cust[] = [];
  cust: Cust[] = [];
  selectedCriteria: string = 'home';
  searchValue: string | any;
  searchId: number | undefined;
  oneCust: Cust | undefined;
  filterCustList: Cust[] = [];
 
  searchcustname: string | undefined;
 
  constructor(private http: HttpClient, private custservice: CustomerserviceService, private router: ActivatedRoute) { }
  ngOnInit() {
    this.custservice.getdata().subscribe((res: any) => {
      this.custlist = [...res];
      this.cust = this.custlist;
    })

  }

  search() {
    // console.log(this.selectedCriteria); 
    if (this.searchcustname == "") {
      this.ngOnInit();
    }
   else if(this.selectedCriteria === "customerNumber") {
      console.log(this.selectedCriteria);
      this.searchnumber();
    }
    else if (this.selectedCriteria === "customerName") {
      console.log("in cust" + this.selectedCriteria);
      this.searchname();
    }
    else if (this.selectedCriteria === "contactLastName") {
      console.log("in cust lastname " + this.selectedCriteria);
      this.searchcontactlastname();
    }
    else if (this.selectedCriteria === "contactFirstName") {
      this.searchcontactfirstname();
    }
    else if (this.selectedCriteria === "phone") {
      this.searchphone();
    }
    else if (this.selectedCriteria === "city") {
      this.searchcity();
    }
    else if (this.selectedCriteria === "state") {
      this.searchstate();
    }
    else if (this.selectedCriteria === "postalCode") {
      this.searchpostalcode();
    }
    else if (this.selectedCriteria === "country") {
      this.searchcountry();
    }
    else if (this.selectedCriteria === "salesRepEmployeeNumber") {
      console.log("iff");
      
      this.searchsalesreponumber();
    }
    else if (this.selectedCriteria==="addressLine1"){
      console.log();
      
      this.searchAdd1();
    }
    else if (this.selectedCriteria==="addressLine2"){
      this.searchAdd2();
    }
   
  }


  searchnumber() {
    for (let oneCust of this.custlist) {
      if (Number(this.searchcustname) == oneCust.customerNumber) {
        this.filterCustList.push(oneCust)
        this.cust = [];
        console.log(Cust);
       // this.cust.push(oneCust);
        this.oneCust = oneCust;
        // break;
      } else {
        this.oneCust = undefined;
      }

    }
  }
  searchcity() {
    this.filterCustList = [];
    for (let oneCust of this.custlist) {
      if (this.searchcustname == oneCust.city) {
        this.filterCustList.push(oneCust)
        this.cust = [];
        console.log(oneCust);
        // this.cust.push(oneCust);
        this.oneCust = oneCust;
        //  break;
      } else {
        this.oneCust = undefined;
      }
    }
  }

  searchname() {
    this.filterCustList = [];
    console.log("in search method" + this.selectedCriteria);
    for (let oneCust of this.custlist) {
      console.log("--"+oneCust.customerName);
      
      if (this.searchcustname == oneCust.customerName) {
        console.log("in if-- search method" + this.selectedCriteria);
        this.filterCustList.push(oneCust)
        this.cust = [];
        console.log(oneCust);
        // this.cust.push(oneCust);
        this.oneCust = oneCust;
        // break;
      } else {
        this.oneCust = undefined;
      }
    }
  }
  searchcontactlastname() {
    this.filterCustList = [];
    for (let oneCust of this.custlist) {
      if (this.searchcustname == oneCust.contactLastName) {
        this.filterCustList.push(oneCust)
        this.cust = [];
        console.log(oneCust);
        // this.cust.push(oneCust);
        this.oneCust = oneCust;
        // break;
      } else {
        this.oneCust = undefined;
      }
    }
  }

  searchcontactfirstname() {
    this.filterCustList = [];
    for (let oneCust of this.custlist) {
      if (this.searchcustname == oneCust.contactFirstName) {
        this.filterCustList.push(oneCust)
        this.cust = [];
        console.log(oneCust);
        // this.cust.push(oneCust);
        this.oneCust = oneCust;
        // break;
      } else {
        this.oneCust = undefined;
      }
    }
  }

  searchphone() {
    this.filterCustList = [];
    for (let oneCust of this.custlist) {
      if (this.searchcustname == oneCust.phone) {
        this.filterCustList.push(oneCust)
        this.cust = [];
        console.log(oneCust);
        this.cust.push(oneCust);
        this.oneCust = oneCust;
        // break;
      } else {
        this.oneCust = undefined;
      }
    }
  }

  searchcountry() {
    this.filterCustList = [];
    for (let oneCust of this.custlist) {
      if (this.searchcustname == oneCust.country) {
        this.filterCustList.push(oneCust)
        this.cust = [];
        console.log(oneCust);
        this.cust.push(oneCust);
        this.oneCust = oneCust;
        console.log('fil' + this.filterCustList);

        // break;
      } else {
        this.oneCust = undefined;
      }
    }
  }

  searchstate() {
    this.filterCustList = [];
    for (let oneCust of this.custlist) {
      if (this.searchcustname == oneCust.state) {
        this.filterCustList.push(oneCust)
        this.cust = [];
        console.log(oneCust);
        // this.cust.push(oneCust);
        this.oneCust = oneCust;
        // break;
      } else {
        this.oneCust = undefined;
      }
    }
  }

  searchpostalcode() {
    this.filterCustList = [];
    for (let oneCust of this.custlist) {
      if (this.searchcustname == oneCust.postalCode) {
        this.filterCustList.push(oneCust)
        this.cust = [];
        console.log(oneCust);
        this.cust.push(oneCust);
        this.oneCust = oneCust;
        // break;
      } else {
        this.oneCust = undefined;
      }
    }
  }

  searchsalesreponumber() {
    this.filterCustList = [];
    console.log("in method");
    for (let oneCust of this.custlist) {
      if (this.searchcustname == oneCust.salesRepEmployeeNumber) {
        console.log("in if method");
        this.cust = [];
        console.log(Cust);

        this.cust.push(oneCust);
        this.oneCust = oneCust;
        //break;
      } else {
        this.oneCust = undefined;
      }
    }
  }

  searchAdd1() {
    this.filterCustList = [];
    for (let oneCust of this.custlist) {
      if (this.searchcustname == oneCust.addressLine1) {
        this.filterCustList.push(oneCust)
        this.cust = [];
        console.log(oneCust);
        // this.cust.push(oneCust);
        this.oneCust = oneCust;
        // break;
      } else {
        this.oneCust = undefined;
      }
    }
  }

  searchAdd2() {
    this.filterCustList = [];
    for (let oneCust of this.custlist) {
      if (this.searchcustname == oneCust.addressLine2) {
        this.filterCustList.push(oneCust)
        this.cust = [];
        console.log(oneCust);
        // this.cust.push(oneCust);
        this.oneCust = oneCust;
        // break;
      } else {
        this.oneCust = undefined;
      }
    }
  }
   
 
}

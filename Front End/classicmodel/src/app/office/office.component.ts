import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OfficeService } from '../services/office.service';
import { Off } from './off';

@Component({
  selector: 'app-office',
  templateUrl: './office.component.html',
  styleUrls: ['./office.component.css']
})
export class OfficeComponent {
  // officeservice: any;
  officelist: Off[] = [];
  off: Off[] = [];
  // office!: number;
  filterOfficeList:Off[]=[];
  searchOfficeCode: any;
  oneoffice: Off | undefined;
  searchOfficeCity: string |undefined;
  constructor(private officeservice: OfficeService) { }

  ngOnInit() {
    this.officeservice.getdata().subscribe((res: any) => {
      this.officelist = [...res];
      this.off = this.officelist;

      console.log(this.off);

    })
  }
  getOfficeDetailsByCode(){
    this.filterOfficeList = [];
    for(let oneoffice of this.officelist){
      if(this.searchOfficeCode==oneoffice.officeCode){
        this.filterOfficeList.push(oneoffice)
        this.off=[];
        console.log(oneoffice);
        this.off.push(oneoffice);
        this.oneoffice=oneoffice;
        break;
      }else{
        this.oneoffice=undefined;
      }
    }
    if (!this.searchOfficeCode) {
      this.off = this.officelist;
    }
  }

  getOfficeDetailsByCity(){
    this.filterOfficeList = [];
    for(let oneoffice of this.officelist){
      if(this.searchOfficeCity==oneoffice.city){
        this.filterOfficeList.push(oneoffice)
        this.off=[];
        // console.log(oneoffice);
        this.off.push(oneoffice);
        this.oneoffice=oneoffice;
        //  break;
      }else{
        this.oneoffice=undefined;
      }
    }
    if (!this.searchOfficeCity) {
      this.off = this.officelist;
    }
  }

}

import { Component } from '@angular/core';
import { ProductserviceService } from 'src/app/services/productservice.service';

@Component({
  selector: 'app-planes',
  templateUrl: './planes.component.html',
  styleUrls: ['./planes.component.css']
})
export class PlanesComponent {
  planes:any[]=[];

  constructor( private productservice:ProductserviceService){}

   ngOnInit(){

     this.productservice.getdata().subscribe((res:any) => {

     this.planes=res.filter((product:any) => product.productLine === 'Planes');

   

 })

  }
}

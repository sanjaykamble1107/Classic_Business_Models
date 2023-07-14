import { Component } from '@angular/core';
import { ProductserviceService } from 'src/app/services/productservice.service';

@Component({
  selector: 'app-motorcycles',
  templateUrl: './motorcycles.component.html',
  styleUrls: ['./motorcycles.component.css']
})
export class MotorcyclesComponent {
  motorcycles:any[]=[];

  constructor( private productservice:ProductserviceService){}

  ngOnInit(){

    this.productservice.getdata().subscribe((res:any) => {

    this.motorcycles=res.filter((product:any) => product.productLine === 'Motorcycles');

    })

  }
}

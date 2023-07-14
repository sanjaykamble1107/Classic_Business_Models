import { Component } from '@angular/core';
import { ProductserviceService } from 'src/app/services/productservice.service';

@Component({
  selector: 'app-ships',
  templateUrl: './ships.component.html',
  styleUrls: ['./ships.component.css']
})
export class ShipsComponent {
  ships:any[]=[];

  constructor(private productservice:ProductserviceService){}

  ngOnInit(){

    this.productservice.getdata().subscribe((res:any) =>

     this.ships=res.filter((product:any) => product.productLine ==='Ships'));

     console.log(this.ships);

     

  }
}

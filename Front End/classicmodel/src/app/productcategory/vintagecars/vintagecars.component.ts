import { Component } from '@angular/core';
import { ProductserviceService } from 'src/app/services/productservice.service';

@Component({
  selector: 'app-vintagecars',
  templateUrl: './vintagecars.component.html',
  styleUrls: ['./vintagecars.component.css']
})
export class VintagecarsComponent {
  vintageCars:any[]=[];

  constructor(private productservice:ProductserviceService){}
 
   ngOnInit(){
 
     this.productservice.getdata().subscribe((res:any) => {
 
       this.vintageCars=res.filter((product:any) => product.productLine === 'Vintage Cars');
 
    
 
   })
 
 
 
 
 }
}

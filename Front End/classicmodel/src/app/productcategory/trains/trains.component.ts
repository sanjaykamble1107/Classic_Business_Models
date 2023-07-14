import { Component } from '@angular/core';
import { ProductserviceService } from 'src/app/services/productservice.service';

@Component({
  selector: 'app-trains',
  templateUrl: './trains.component.html',
  styleUrls: ['./trains.component.css']
})
export class TrainsComponent {
  trains: any[] = [];

  constructor(private productservice: ProductserviceService) { }

  ngOnInit() {

    this.productservice.getdata().subscribe((res: any) => {

      this.trains = res.filter((product: any) => product.productLine === 'Trains');


    })

  }


}

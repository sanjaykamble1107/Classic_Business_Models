import { Component } from '@angular/core';
import { ProductserviceService } from 'src/app/services/productservice.service';

@Component({
  selector: 'app-busesandtrucks',
  templateUrl: './busesandtrucks.component.html',
  styleUrls: ['./busesandtrucks.component.css']
})
export class BusesandtrucksComponent {
  trucksAndBuses: any[] = [];

  constructor(private productservice: ProductserviceService) { }

  ngOnInit() {

    this.productservice.getdata().subscribe((res: any) => {

      this.trucksAndBuses = res.filter((product: any) => product.productLine === 'Trucks and Buses');



    })

  }
}

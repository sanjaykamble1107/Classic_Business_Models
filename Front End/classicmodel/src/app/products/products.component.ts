import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProductserviceService } from '../services/productservice.service';
import { Lines } from './lines';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent {

  productlist: any[] = [];
  productlines: Lines[] = [];
  constructor(private router: Router, private productservice: ProductserviceService,) {

  }

  p: Lines = new Lines();

  pp: Lines[] = [];




  openArticle(topic: string) {
    this.router.navigate(['/articles', topic]);
  }
  ngOnInit() {

    this.p.productLine = "cycles";
    this.p.textDescription = "New Cycles from Mumbai";
    this.p.htmlDescription = "cycles";
    this.p.image = "/assets/cycles.jpg";

    let p2: Lines = new Lines();

    p2.productLine = "Bullet";
    p2.textDescription = "New Bullet type concept bike";
    p2.textDescription = "New Bullet type now in market";
    p2.image = "/assets/bullet.jpg";

    this.pp.push(this.p);
    this.pp.push(p2);


    this.productservice.getdata().subscribe((res: any) => {

      this.productlist = [...res];

      console.log(this.productlist);

      // console.log('Classic Cars',this.classicCars);
    })
      

    this.productservice.getproductlines().subscribe(data=>{
      this.pp=[...data];
      console.log(data);
      
      console.log(this.pp);
      
    })


}
  }



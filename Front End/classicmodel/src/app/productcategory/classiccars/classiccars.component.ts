import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductserviceService } from 'src/app/services/productservice.service';

@Component({
  selector: 'app-classiccars',
  templateUrl: './classiccars.component.html',
  styleUrls: ['./classiccars.component.css']
})
export class ClassiccarsComponent {
  classicCars: any[] = [];
  productLine:string=""
  constructor(private productservice: ProductserviceService,private router:ActivatedRoute) { }

  ngOnInit() {

    let productLine = this.router.snapshot.params['productLine']
    

    this.productservice.getdata().subscribe((res: any) => {

      this.classicCars = res.filter((product: any) => product.productLine === productLine);



    })

  }
}

import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductserviceService } from 'src/app/services/productservice.service';

@Component({
  selector: 'app-update-buy-price',
  templateUrl: './update-buy-price.component.html',
  styleUrls: ['./update-buy-price.component.css']
})
export class UpdateBuyPriceComponent {
  product: any={};

  constructor(private productService: ProductserviceService, private router: ActivatedRoute,private routerr:Router) {
    this.router.params.subscribe(params => {
      this.product.productCode = params['productCode'];
    });
  }

  updateBuyPrice(productCode: string, buyPrice: number) {
    this.productService.updateBuyPrice(productCode, buyPrice)
      .subscribe(
        response => {
          console.log(response);
          // Handle success

          alert("product updated successfully")

          this.goToUpdateList();
        },
        error => {
          console.log(error);
          // Handle error
          
        }
      );
  }

  onSubmit() {
    console.log(this.product);
     
  }

  goToUpdateList() {
    this.routerr.navigate(['/updateproduct']);
  }
}

import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductserviceService } from 'src/app/services/productservice.service';

@Component({
  selector: 'app-update-quantity-in-stock',
  templateUrl: './update-quantity-in-stock.component.html',
  styleUrls: ['./update-quantity-in-stock.component.css']
})
export class UpdateQuantityInStockComponent {
  product: any={};

  constructor(private productService: ProductserviceService, private router: ActivatedRoute,private routerr:Router) {
    this.router.params.subscribe(params => {
      this.product.productCode = params['productCode'];
    });
  }

  updateQuantityInStock(productCode: string, quantityInStock: number) {
    this.productService.updateStock(productCode, quantityInStock)
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

import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductserviceService } from 'src/app/services/productservice.service';

@Component({
  selector: 'app-updateproduct',
  templateUrl: './updateproduct.component.html',
  styleUrls: ['./updateproduct.component.css']
})
export class UpdateproductComponent {
  product: any = {};

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
        },
        error => {
          console.log(error);
          // Handle error
        }
      );
  }

  updateQuantityInStock(productCode: string, quantityInStock: number) {
    this.productService.updateStock(productCode, quantityInStock)
      .subscribe(
        response => {
         
          console.log(response);
          // Handle success
        },
        error => {
          console.log(error);
          // Handle error
        }
      );
  }

  updateMsrp(productCode: string, msrp: number) {
    this.productService.updateMsrp(productCode, msrp)
      .subscribe(
        response => {
          console.log(response);
          // Handle success
        },
        error => {
          console.log(error);
          // Handle error
        }
      );
  }

  updateProductVendor(productCode: string, productVendor: string) {
    this.productService.updateVendor(productCode, productVendor)
      .subscribe(
        response => {
          console.log(response);
          // Handle success
        },
        error => {
          console.log(error);
          // Handle error
        }
      );
  }
 
  updateProductName(productCode: string, productName: string) {
    this.productService.updateName(productCode, productName)
      .subscribe(
        response => {
          console.log(response);
          // Handle success
        },
        error => {
          console.log(error);
          // Handle error
        }
      );
  }


}

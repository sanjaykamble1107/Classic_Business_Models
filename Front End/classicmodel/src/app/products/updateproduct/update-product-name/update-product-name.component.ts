import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductserviceService } from 'src/app/services/productservice.service';

@Component({
  selector: 'app-update-product-name',
  templateUrl: './update-product-name.component.html',
  styleUrls: ['./update-product-name.component.css']
})
export class UpdateProductNameComponent {
  product: any={};

  constructor(private productService: ProductserviceService, private router: ActivatedRoute,private routerr:Router) {
    this.router.params.subscribe(params => {
      this.product.productCode = params['productCode'];
    });
  }

  updateProductName(productCode: string, productName: string) {
    this.productService.updateName(productCode, productName)
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
          this.goToUpdateList();
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

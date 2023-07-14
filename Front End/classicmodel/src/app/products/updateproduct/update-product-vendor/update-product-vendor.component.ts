import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductserviceService } from 'src/app/services/productservice.service';

@Component({
  selector: 'app-update-product-vendor',
  templateUrl: './update-product-vendor.component.html',
  styleUrls: ['./update-product-vendor.component.css']
})
export class UpdateProductVendorComponent {
  product: any={};

  constructor(private productService: ProductserviceService, private router: ActivatedRoute,private routerr:Router) {
    this.router.params.subscribe(params => {
      this.product.productCode = params['productCode'];
    });
  }

  updateProductVendor(productCode: string, productVendor: string) {
    this.productService.updateVendor(productCode, productVendor)
      .subscribe(
        response => {
          console.log(response);
          // Handle success
          // Redirect to the showproducts page
          this.routerr.navigate(['/showproducts']);
        },
        error => {
          console.log(error);
          // Handle error
          this.goToUpdateList();
          alert("product updated successfully")

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

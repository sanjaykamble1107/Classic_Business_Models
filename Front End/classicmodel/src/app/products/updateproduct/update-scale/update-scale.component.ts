import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductserviceService } from 'src/app/services/productservice.service';

@Component({
  selector: 'app-update-scale',
  templateUrl: './update-scale.component.html',
  styleUrls: ['./update-scale.component.css']
})
export class UpdateScaleComponent {
  product: any={};

  constructor(private productService: ProductserviceService, private router: ActivatedRoute,private routerr:Router) {
    this.router.params.subscribe(params => {
      this.product.productCode = params['productCode'];
    });
  }

  updateProductScale(productCode: string, productScale: string) {
    this.productService.updateScale(productCode, productScale)
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

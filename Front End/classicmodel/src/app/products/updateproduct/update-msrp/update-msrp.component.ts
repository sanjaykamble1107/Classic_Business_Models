import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductserviceService } from 'src/app/services/productservice.service';

@Component({
  selector: 'app-update-msrp',
  templateUrl: './update-msrp.component.html',
  styleUrls: ['./update-msrp.component.css']
})
export class UpdateMsrpComponent {
  product: any={};

  constructor(private productService: ProductserviceService, private router: ActivatedRoute,private routerr:Router) {
    this.router.params.subscribe(params => {
      this.product.productCode = params['productCode'];
    });
  }

  updateProductMsrp(productCode: string, msrp: number) {
    this.productService.updateMsrp(productCode, msrp)
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

import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Prod } from '../prod';
import { ProductserviceService } from '../../services/productservice.service';

@Component({
  selector: 'app-addproduct',
  templateUrl: './addproduct.component.html',
  styleUrls: ['./addproduct.component.css']
})
export class AddproductComponent {
  product: Prod = new Prod();

  constructor(private productservice: ProductserviceService, private router: Router) { }

  ngOnInit(): void {

    throw new Error('Method not implemented.');

  }

  saveProduct() {
    // this.e = this.employee;
    //console.log(this.e);
    this.productservice.createProduct(this.product).subscribe({
      next: (data) => {
        console.log(data);
        this.goToProductList();
      },
      error: (error) => {
        console.error('Error creating product:',error);
      }
    });
  }
formSubmitted= false;
  onSubmit() {
    console.log(this.product);
    if (
      !this.product.buyPrice||
      !this.product.msrp||
      !this.product.productCode||
      !this.product.productName||
      !this.product.quantityInStock||
      !this.product.productVendor||
      !this.product.productDescription||
      !this.product.productScale
    ){
      this.formSubmitted=true;
      return;
    }
    this.saveProduct();
    // this.e = this.employee;
    this.router.navigate(['/products']);
    //console.log(this.e);
  }

  goToProductList() {

  }
}

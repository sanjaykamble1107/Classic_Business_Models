import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductserviceService } from 'src/app/services/productservice.service';
import { Prod } from '../prod';

@Component({
  selector: 'app-showproducts',
  templateUrl: './showproducts.component.html',
  styleUrls: ['./showproducts.component.css']
})
export class ShowproductsComponent {
  isProductQuantityVisible = false;
  products!:any[];
  productlist: Prod[] = [];
  filteredProductList: Prod[] = [];
  selectedCriteria: string = 'home';
  searchValue: string | any;
  searchproductcode: any;
  searchname: any;
  searchprodname: string |any ;
  oneProd: Prod | undefined;
  searchCode: any | undefined;
  prod: Prod[] = [];
  totalOrderedQuantity: number | undefined;
totalSaleForProduct: string | undefined;
totalSale: string | undefined;
highDemandProduct: Prod[] = [];
  istotalSaleForProductVisible = false;
  istotalSaleVisible=false;
  ishighDemandProductVisible=false;

  constructor(private http:HttpClient, private router: Router, private productservice: ProductserviceService, private routerr: ActivatedRoute) { }

  ngOnInit() {

    this.productservice.getdata().subscribe((res: any) => {

      this.productlist = [...res];

      console.log(this.productlist);
      this.prod=this.productlist;
     

    })

  }

  search() {

    console.log(this.selectedCriteria);
    if (this.selectedCriteria === "productCode") {
      console.log(this.selectedCriteria);
      this.searchproductCode();
    }
    else if (this.selectedCriteria === "productName") {
      console.log("in prod" + this.selectedCriteria);
      this.searchproductname();
    }
    else if(this.selectedCriteria === "productScale"){
      console.log("in prod " + this.selectedCriteria);
      this.searchproductscale();
    }
    else if(this.selectedCriteria === "productVendor"){
      console.log("in prod " + this.selectedCriteria);
      this.searchproductvendor();
    }
    else if (this.selectedCriteria === 'totalSale') {
    this.fetchTotalSale();
  } else if (this.selectedCriteria === 'highDemandProducts') {
    this. fetchHighDemandProduct();
  } else if (this.selectedCriteria === 'totalOrderedQuantity') {
    console.log(this.selectedCriteria);
   
    this.fetchTotalOrderedQuantity();
  } else if (this.selectedCriteria === 'totalSaleForProduct') {
    this.fetchTotalSaleForProduct();
  }
  }
  searchproductvendor() {
    this.filteredProductList = [];
    for (let oneProd of this.productlist) {
      if (this.searchprodname == oneProd.productVendor) {
        this.filteredProductList.push(oneProd)
        this.prod = [];
        console.log(oneProd);
        this.prod.push(oneProd);
        this.oneProd = oneProd;
        // this.fetchTotalOrderedQuantity(oneProd.productCode);
        // console.log("FilterList  " + this.filteredProductList[0].productName);

      }
      else {
        this.oneProd = undefined;
      }
    }
  }
 
  searchproductscale() {
    this.filteredProductList = [];
    for (let oneProd of this.productlist) {
      if (this.searchprodname == oneProd.productScale) {
        this.filteredProductList.push(oneProd)
        this.prod = [];
        console.log(oneProd);
        this.prod.push(oneProd);
        this.oneProd = oneProd;
        // console.log("FilterList  " + this.filteredProductList[0].productName);

      }
      else {
        this.oneProd = undefined;
      }
    }
  }
  searchproductname() {
    this.filteredProductList = [];
    for (let oneProd of this.productlist) {
      if (this.searchprodname == oneProd.productName) {
        this.filteredProductList.push(oneProd)
        this.prod = [];
        console.log(oneProd);
        this.prod.push(oneProd);
        this.oneProd = oneProd;
        // console.log("FilterList  " + this.filteredProductList[0].productName);

      }
      else {
        this.oneProd = undefined;
      }
    }
  }
  searchproductCode() {
    this.filteredProductList = [];
    for (let oneProd of this.productlist) {
      if (this.searchprodname == oneProd.productCode) {
        this.prod = [];
        console.log(Prod);
        this.prod.push(oneProd);
        this.oneProd = oneProd;
      }
      else {
        this.oneProd = undefined;
      }
    }
  }
   
  fetchTotalOrderedQuantity() {
    const productCode = this.searchprodname;
    console.log("product---"+productCode);
   
    this.productservice
      .getTotalOrderedQuantity(productCode)
      .subscribe(
        (quantity) => {
          this.isProductQuantityVisible = true;
          this.totalOrderedQuantity = quantity;
        //  alert(`Total Ordered Quantity for Product: ${this.totalOrderedQuantity}`);
        },
        (error) => {
          console.log(error);
        }
      );
  }
 
  fetchTotalSaleForProduct() {
    const productCode = this.searchprodname;
    console.log("product---"+productCode);
    this.productservice
      .getTotalSaleForProduct(productCode)
      .subscribe(
        (sale) => {
          this.istotalSaleForProductVisible = true;
          this.totalSaleForProduct = sale;
          //console.log(this.totalSaleForProduct);
         
         // alert(`Total Sale for Product: ${this.totalSaleForProduct}`);
        },
        (error) => {
          console.log(error);
        }
      );
  }
 
  fetchTotalSale() {
    this.productservice.getTotalSale().subscribe(
      (sale) => {
        this.istotalSaleVisible=true;
        this.totalSale = sale;
        //alert(`Total Sale for Product: ${this.totalSale}`);
      },
      (error) => {
        console.log(error);
      }
    );
  }
 
  fetchHighDemandProduct() {
    this.productservice.getHighDemandProduct().subscribe(
      (products) => {
        this.ishighDemandProductVisible=true;
        this.highDemandProduct = products;
        // const productNames = this.highDemandProduct.map((product) => product.productName).join(', ');
        // alert(`Highly Demanded Product: ${productNames}`);
      },
      (error) => {
        console.log(error);
      }
    );
  }

}

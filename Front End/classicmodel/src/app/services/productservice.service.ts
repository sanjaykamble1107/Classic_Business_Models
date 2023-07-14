import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Lines } from '../products/lines';
import { Prod } from '../products/prod';

@Injectable({
  providedIn: 'root'
})
export class ProductserviceService {

  constructor(private _http:HttpClient) { }

  getdata(){

    return this._http.get('http://localhost:5020/api/v1/products/all_product_details');

  }

  getproductlines():Observable<any>{
    console.log(Lines);

    //console.log(this._http.get<Lines>(`http://localhost:5020/api/v1/product_lines/all_productline_details`).subscribe(data=>{data}));
    
    
    return this._http.get<Lines>(`http://localhost:5020/api/v1/product_lines/all_productline_details`);
  }

  createProductLine(productLine: Lines) {

    return this._http.post(`http://localhost:5020/api/v1/product_lines/`, productLine);

  }
  createProduct(product: Prod) :Observable<Object>{

    console.log(product)

    return this._http.post('http://localhost:5020/api/v1/products/',product);

  }
  updateBuyPrice(productCode: string, buyPrice: number): Observable<any> {
    const url = `http://localhost:5020/api/v1/products/update_buy_price/${productCode}/${buyPrice}`;
    return this._http.put(url, null);
  }
 
  // Update Quantity in Stock by Product Code
  updateStock(productCode: string, quantityInStock: number): Observable<any> {
    const url = `http://localhost:5020/api/v1/products/update_stock/${productCode}/${quantityInStock}`;
    return this._http.put(url, null);
  }
 
  // Update MSRP by Product Code
  updateMsrp(productCode: string, msrp: number): Observable<any> {
    const url = `http://localhost:5020/api/v1/products/update_msrp/${productCode}/${msrp}`;
    return this._http.put(url, null);
  }
 
  // Update Product Vendor by Product Code
  updateVendor(productCode: string, productVendor: string): Observable<any> {
    const url = `http://localhost:5020/api/v1/products/update_vendor/${productCode}/${productVendor}`;
    return this._http.put(url, null);
  }
 
  // Update Product Scale by Product Code
  updateScale(productCode: string, productScale: string): Observable<any> {
    const url = `http://localhost:5020/api/v1/products/update_scale/${productCode}/${productScale}`;
    return this._http.put(url, null);
  }
 
  // Update Product Name by Product Code
  updateName(productCode: string, productName: string): Observable<any> {
    const url = `http://localhost:5020/api/v1/products/update_name/${productCode}/${productName}`;
    return this._http.put(url, null);
  }

  getTotalOrderedQuantity(productCode: string): Observable<any> {
    const url = `http://localhost:5020/api/v1/products/${productCode}/total_oredered_qty`;
    return this._http.get<string>(url,{ responseType: 'text' as 'json' });
  }

  // Fetch Total Sale for a Product
  getTotalSaleForProduct(productCode: string): Observable<any> {
    const url = `http://localhost:5020/api/v1/products/${productCode}/total_sale`;
    return this._http.get<string>(url,{responseType:'text'as 'json'});
  }

  // Fetch Total Sale for Each Product
  getTotalSale(): Observable<string> {
    const url = 'http://localhost:5020/api/v1/products/total_sale';
    return this._http.get<string>(url,{responseType:'text' as 'json'});
  }

  // Fetch Highly Demanded Products
  getHighDemandProduct(): Observable<Prod[]> {
    const url = 'http://localhost:5020/api/v1/products/product_details';
    return this._http.get<Prod[]>(url,{responseType:'text' as 'json'});
  }


  getProductByProductLine(productLine:string)
  {
  //  const url = `http://localhost:5020/api/v1/product_lines/product_line/${}`
  }
}

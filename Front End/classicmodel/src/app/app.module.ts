import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomepageComponent } from './homepage/homepage.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { ProductsComponent } from './products/products.component';
import { EmployeesComponent } from './employees/employees.component';
import { CustomersComponent } from './customers/customers.component';
import { VintagecarsComponent } from './productcategory/vintagecars/vintagecars.component';
import { ClassiccarsComponent } from './productcategory/classiccars/classiccars.component';
import { MotorcyclesComponent } from './productcategory/motorcycles/motorcycles.component';
import { BusesandtrucksComponent } from './productcategory/busesandtrucks/busesandtrucks.component';
import { PlanesComponent } from './productcategory/planes/planes.component';
import { ShipsComponent } from './productcategory/ships/ships.component';
import { HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddempComponent } from './addemp/addemp.component';
import { OfficeComponent } from './office/office.component';
import { AddofficeComponent } from './addoffice/addoffice.component';
import { UpdateComponent } from './update/update.component';
import { UpdateofficeComponent } from './office/updateoffice/updateoffice.component';
import { AddcustComponent } from './addcust/addcust.component';
import { UpdatecustomerComponent } from './customers/updatecustomer/updatecustomer.component';
import { AddproductComponent } from './products/addproduct/addproduct.component';
import { OrdersComponent } from './orders/orders.component';
import { OrderdetailsComponent } from './orderdetails/orderdetails.component';
import { AddorderComponent } from './addorder/addorder.component';
import { UpdateorderComponent } from './orders/updateorder/updateorder.component';
import { AddorderdetailsComponent } from './addorderdetails/addorderdetails.component';
import { UpdateordredetailsComponent } from './orderdetails/updateordredetails/updateordredetails.component';
import { ShowproductsComponent } from './products/showproducts/showproducts.component';
import { UpdateproductComponent } from './products/updateproduct/updateproduct.component';
import { UpdateBuyPriceComponent } from './products/updateproduct/update-buy-price/update-buy-price.component';
import { UpdateMsrpComponent } from './products/updateproduct/update-msrp/update-msrp.component';
import { UpdateProductNameComponent } from './products/updateproduct/update-product-name/update-product-name.component';
import { UpdateProductVendorComponent } from './products/updateproduct/update-product-vendor/update-product-vendor.component';
import { UpdateQuantityInStockComponent } from './products/updateproduct/update-quantity-in-stock/update-quantity-in-stock.component';
import { UpdateScaleComponent } from './products/updateproduct/update-scale/update-scale.component';
import { TrainsComponent } from './productcategory/trains/trains.component';
import { ProductlinesComponent } from './products/productlines/productlines.component';
import { PaymentsComponent } from './payments/payments.component';
import { AddpaymentComponent } from './payments/addpayment/addpayment.component';
import { UpdatepaymentComponent } from './payments/updatepayment/updatepayment.component';
import { AuthInterceptor } from './services/auth.service';




@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    HeaderComponent,
    LoginComponent,
    ProductsComponent,
    EmployeesComponent,
    CustomersComponent,
    VintagecarsComponent,
    ClassiccarsComponent,
    MotorcyclesComponent,
    BusesandtrucksComponent,
    PlanesComponent,
    ShipsComponent,
    AddempComponent,
    OfficeComponent,
    AddofficeComponent,
    UpdateComponent,
    UpdateofficeComponent,
    AddcustComponent,
    UpdatecustomerComponent,
    AddproductComponent,
    OrdersComponent,
    OrderdetailsComponent,
    AddorderComponent,
    UpdateorderComponent,
    AddorderdetailsComponent,
    UpdateordredetailsComponent,
    ShowproductsComponent,
    UpdateproductComponent,
    UpdateBuyPriceComponent,
    UpdateMsrpComponent,
    UpdateProductNameComponent,
    UpdateProductVendorComponent,
    UpdateQuantityInStockComponent,
    UpdateScaleComponent,
    TrainsComponent,
    ProductlinesComponent,
    PaymentsComponent,
    AddpaymentComponent,
    UpdatepaymentComponent,
    
    
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [{
    provide:HTTP_INTERCEPTORS,
    useClass:AuthInterceptor,
    multi:true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }

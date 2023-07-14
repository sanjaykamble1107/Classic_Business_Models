import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddempComponent } from './addemp/addemp.component';
import { AddofficeComponent } from './addoffice/addoffice.component';

import { CustomersComponent } from './customers/customers.component';
import { EmployeesComponent } from './employees/employees.component';
import { HeaderComponent } from './header/header.component';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';
import { OfficeComponent } from './office/office.component';
import { BusesandtrucksComponent } from './productcategory/busesandtrucks/busesandtrucks.component';
import { ClassiccarsComponent } from './productcategory/classiccars/classiccars.component';
import { MotorcyclesComponent } from './productcategory/motorcycles/motorcycles.component';
import { PlanesComponent } from './productcategory/planes/planes.component';
import { ShipsComponent } from './productcategory/ships/ships.component';
import { VintagecarsComponent } from './productcategory/vintagecars/vintagecars.component';
import { ProductsComponent } from './products/products.component';
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
import { ProductserviceService } from './services/productservice.service';
import { ProductlinesComponent } from './products/productlines/productlines.component';
import { UpdateQuantityInStockComponent } from './products/updateproduct/update-quantity-in-stock/update-quantity-in-stock.component';
import { UpdateScaleComponent } from './products/updateproduct/update-scale/update-scale.component';
import { UpdateProductVendorComponent } from './products/updateproduct/update-product-vendor/update-product-vendor.component';
import { UpdateProductNameComponent } from './products/updateproduct/update-product-name/update-product-name.component';
import { UpdateMsrpComponent } from './products/updateproduct/update-msrp/update-msrp.component';
import { PaymentsComponent } from './payments/payments.component';
import { AddpaymentComponent } from './payments/addpayment/addpayment.component';
import { UpdatepaymentComponent } from './payments/updatepayment/updatepayment.component';
import { UserGuard } from './AuthGuard/user.guard';
import { UpdateBuyPriceComponent } from './products/updateproduct/update-buy-price/update-buy-price.component';
// import { UpdateComponent } from './update/update.component';

const routes: Routes = [{ path: "", redirectTo: "home", pathMatch: "full" },

{ path: "homepage", component: HomepageComponent },

{ path: "products", component: ProductsComponent, canActivate: [UserGuard] },

{ path: "vintagecars", component: VintagecarsComponent },

{ path: "classiccars/:productLine", component: ClassiccarsComponent },

{ path: "motorcycles", component: MotorcyclesComponent },

{ path: "busesandtrucks", component: BusesandtrucksComponent },

{ path: "ships", component: ShipsComponent },

{ path: "planes", component: PlanesComponent },

{ path: "employees", component: EmployeesComponent, canActivate: [UserGuard] },

{ path: "customers", component: CustomersComponent, canActivate: [UserGuard] },

{ path: "login", component: LoginComponent },

{ path: "addemp", component: AddempComponent },

{ path: "office", component: OfficeComponent },

{ path: 'update/:employeeNumber', component: UpdateComponent },

{ path: 'updateoffice/:officeCode', component: UpdateofficeComponent },

{ path: "addoffice", component: AddofficeComponent },

{ path: "addcust", component: AddcustComponent },

{ path: "updatecustomer/:customerNumber", component: UpdatecustomerComponent },

{ path: "addproduct", component: AddproductComponent },

{ path: "orders", component: OrdersComponent },

{ path: "orderdetails", component: OrderdetailsComponent },

{ path: "addorder", component: AddorderComponent },

{ path: "updateorder/:orderNumber", component: UpdateorderComponent },

{ path: "addorderdetails", component: AddorderdetailsComponent },

{ path: "updateorderdetails/:orderNumber/:productCode", component: UpdateordredetailsComponent },

{ path: "showproducts", component: ShowproductsComponent },

{ path: "updateproduct", component: UpdateproductComponent },
{ path: "productlines", component: ProductlinesComponent },
{ path: "update-quantity-in-stock", component: UpdateQuantityInStockComponent },

{ path: "update-scale", component: UpdateScaleComponent },

{ path: "update-product-vendor", component: UpdateProductVendorComponent },

{ path: "update-product-name", component: UpdateProductNameComponent },

{ path: "update-msrp", component: UpdateMsrpComponent },

{ path: "update-buy-price", component: UpdateBuyPriceComponent },

{ path: "payments", component: PaymentsComponent },

{ path: "addpayment", component: AddpaymentComponent },

{ path: "updatepayment", component: UpdatepaymentComponent },
{ path: "**", redirectTo: "home" }];



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

export class OrdDetails{
   orderNumber:number;
   productCode:string;
   quantityOrdered:number;
   priceEach:number;
   orderLineNumber:number;

   constructor(){
    this.orderNumber=0;
    this.productCode="";
    this.quantityOrdered=0;
    this.priceEach=0;
    this.orderLineNumber=0;
   }

}
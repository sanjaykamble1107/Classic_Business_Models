export class Cust {

    customerNumber: number;
    customerName: string;
    contactLastName: string;
    contactFirstName: string
    phone: string;
    addressLine1: string;
    addressLine2: string;
    city: string;
    state: string
    postalCode: string;
    country: string;
    salesRepEmployeeNumber: string;
    creditLimit: number;

    constructor() {
        this.customerNumber = 0;
        this.customerName = "";
        this.contactLastName = " ";
        this.contactFirstName = " ";
        this.phone = " ";
        this.addressLine1 = " ";
        this.addressLine2 = " ";
        this.city = " ";
        this.state = " ";
        this.postalCode = " ";
        this.country = " ";
        this.salesRepEmployeeNumber = "";
        this.creditLimit = 0;
    }
}
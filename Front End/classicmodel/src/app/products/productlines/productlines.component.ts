import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProductserviceService } from 'src/app/services/productservice.service';
import { Lines } from '../lines';

@Component({
  selector: 'app-productlines',
  templateUrl: './productlines.component.html',
  styleUrls: ['./productlines.component.css']
})
export class ProductlinesComponent {
  lines: Lines=new Lines();
  selectedFile!: File;
  fileName: string | undefined;
  path!:string;
  constructor(private productservice:ProductserviceService,private router:Router){}
  onSubmit() {
   //this.fileReading();
   const fileReader = new FileReader();
 fileReader.onload = (e) => {
   const fileData = fileReader.result;
   const imgPath = `/assets/${this.fileName}`;

   // Save the image path to the variable (e.g., lines.image)
   this.lines.image = imgPath;

   // Additional logic to handle file upload or further processing
   // For example, you can send the file to a server using HttpClient
  
   console.log('Image uploaded successfully!');
   this.productservice.createProductLine(this.lines).subscribe((res: any) => {
     console.log('Product line added:', res);
     this.router.navigate(['/products']);
   });
};

fileReader.readAsDataURL(this.selectedFile);

   this.lines.image = this.path;
   console.log("path"+this.path);
 }



onFileSelected(event: any) {
 this.selectedFile = event.target.files[0];
 this.fileName = this.selectedFile.name;
}


}

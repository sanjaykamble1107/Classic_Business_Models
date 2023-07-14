import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css'],
  
  
})
export class HomepageComponent implements OnInit{


  ngOnInit() {
    // Scroll to the "services" section when the button is clicked
    const servicesButton = document.querySelector('a[routerLink="services"]');
    if (servicesButton) {
      servicesButton.addEventListener('click', (event) => {
        event.preventDefault(); // Prevent the default link behavior
        const servicesSection = document.querySelector('#services');
        if (servicesSection) {
          servicesSection.scrollIntoView({ behavior: 'smooth' });
        }
      });
    }
  }


}

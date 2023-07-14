import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateProductVendorComponent } from './update-product-vendor.component';

describe('UpdateProductVendorComponent', () => {
  let component: UpdateProductVendorComponent;
  let fixture: ComponentFixture<UpdateProductVendorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateProductVendorComponent]
    });
    fixture = TestBed.createComponent(UpdateProductVendorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

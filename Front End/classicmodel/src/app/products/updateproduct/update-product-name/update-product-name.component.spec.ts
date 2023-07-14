import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateProductNameComponent } from './update-product-name.component';

describe('UpdateProductNameComponent', () => {
  let component: UpdateProductNameComponent;
  let fixture: ComponentFixture<UpdateProductNameComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateProductNameComponent]
    });
    fixture = TestBed.createComponent(UpdateProductNameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

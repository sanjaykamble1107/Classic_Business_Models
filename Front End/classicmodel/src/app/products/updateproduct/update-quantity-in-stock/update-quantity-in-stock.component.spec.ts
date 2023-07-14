import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateQuantityInStockComponent } from './update-quantity-in-stock.component';

describe('UpdateQuantityInStockComponent', () => {
  let component: UpdateQuantityInStockComponent;
  let fixture: ComponentFixture<UpdateQuantityInStockComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateQuantityInStockComponent]
    });
    fixture = TestBed.createComponent(UpdateQuantityInStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

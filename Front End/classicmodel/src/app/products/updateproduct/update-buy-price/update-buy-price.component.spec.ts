import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateBuyPriceComponent } from './update-buy-price.component';

describe('UpdateBuyPriceComponent', () => {
  let component: UpdateBuyPriceComponent;
  let fixture: ComponentFixture<UpdateBuyPriceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateBuyPriceComponent]
    });
    fixture = TestBed.createComponent(UpdateBuyPriceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

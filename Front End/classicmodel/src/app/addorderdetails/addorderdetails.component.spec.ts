import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddorderdetailsComponent } from './addorderdetails.component';

describe('AddorderdetailsComponent', () => {
  let component: AddorderdetailsComponent;
  let fixture: ComponentFixture<AddorderdetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddorderdetailsComponent]
    });
    fixture = TestBed.createComponent(AddorderdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateordredetailsComponent } from './updateordredetails.component';

describe('UpdateordredetailsComponent', () => {
  let component: UpdateordredetailsComponent;
  let fixture: ComponentFixture<UpdateordredetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateordredetailsComponent]
    });
    fixture = TestBed.createComponent(UpdateordredetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

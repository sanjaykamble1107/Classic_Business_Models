import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateMsrpComponent } from './update-msrp.component';

describe('UpdateMsrpComponent', () => {
  let component: UpdateMsrpComponent;
  let fixture: ComponentFixture<UpdateMsrpComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateMsrpComponent]
    });
    fixture = TestBed.createComponent(UpdateMsrpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddofficeComponent } from './addoffice.component';

describe('AddofficeComponent', () => {
  let component: AddofficeComponent;
  let fixture: ComponentFixture<AddofficeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddofficeComponent]
    });
    fixture = TestBed.createComponent(AddofficeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

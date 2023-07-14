import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MotorcyclesComponent } from './motorcycles.component';

describe('MotorcyclesComponent', () => {
  let component: MotorcyclesComponent;
  let fixture: ComponentFixture<MotorcyclesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MotorcyclesComponent]
    });
    fixture = TestBed.createComponent(MotorcyclesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

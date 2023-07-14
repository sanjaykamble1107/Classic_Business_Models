import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusesandtrucksComponent } from './busesandtrucks.component';

describe('BusesandtrucksComponent', () => {
  let component: BusesandtrucksComponent;
  let fixture: ComponentFixture<BusesandtrucksComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BusesandtrucksComponent]
    });
    fixture = TestBed.createComponent(BusesandtrucksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

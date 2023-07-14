import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VintagecarsComponent } from './vintagecars.component';

describe('VintagecarsComponent', () => {
  let component: VintagecarsComponent;
  let fixture: ComponentFixture<VintagecarsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VintagecarsComponent]
    });
    fixture = TestBed.createComponent(VintagecarsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

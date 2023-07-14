import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateScaleComponent } from './update-scale.component';

describe('UpdateScaleComponent', () => {
  let component: UpdateScaleComponent;
  let fixture: ComponentFixture<UpdateScaleComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateScaleComponent]
    });
    fixture = TestBed.createComponent(UpdateScaleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

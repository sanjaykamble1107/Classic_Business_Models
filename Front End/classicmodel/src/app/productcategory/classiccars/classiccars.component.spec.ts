import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassiccarsComponent } from './classiccars.component';

describe('ClassiccarsComponent', () => {
  let component: ClassiccarsComponent;
  let fixture: ComponentFixture<ClassiccarsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClassiccarsComponent]
    });
    fixture = TestBed.createComponent(ClassiccarsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

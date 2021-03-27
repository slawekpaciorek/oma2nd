import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemoSetupComponent } from './demo-setup.component';

describe('DemoSetupComponent', () => {
  let component: DemoSetupComponent;
  let fixture: ComponentFixture<DemoSetupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DemoSetupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DemoSetupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

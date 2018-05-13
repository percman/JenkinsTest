import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeSplashComponent } from './employee-splash.component';

describe('EmployeeSplashComponent', () => {
  let component: EmployeeSplashComponent;
  let fixture: ComponentFixture<EmployeeSplashComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeSplashComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeSplashComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

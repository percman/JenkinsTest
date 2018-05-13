import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginService } from './login.service';

describe('LoginService', () => {
  let component: LoginService;
  let fixture: ComponentFixture<LoginService>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginService);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

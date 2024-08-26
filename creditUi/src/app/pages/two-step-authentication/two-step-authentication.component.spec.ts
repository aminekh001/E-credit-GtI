import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TwoStepAuthenticationComponent } from './two-step-authentication.component';

describe('TwoStepAuthenticationComponent', () => {
  let component: TwoStepAuthenticationComponent;
  let fixture: ComponentFixture<TwoStepAuthenticationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TwoStepAuthenticationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TwoStepAuthenticationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

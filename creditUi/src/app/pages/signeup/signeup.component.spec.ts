import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SigneupComponent } from './signeup.component';

describe('SigneupComponent', () => {
  let component: SigneupComponent;
  let fixture: ComponentFixture<SigneupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SigneupComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SigneupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

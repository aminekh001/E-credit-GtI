import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultationDesDemandesComponent } from './consultation-des-demandes.component';

describe('ConsultationDesDemandesComponent', () => {
  let component: ConsultationDesDemandesComponent;
  let fixture: ComponentFixture<ConsultationDesDemandesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConsultationDesDemandesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ConsultationDesDemandesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

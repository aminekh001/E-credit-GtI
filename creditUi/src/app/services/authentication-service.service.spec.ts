import { TestBed } from '@angular/core/testing';

import { AuthenticatisonService } from './authentication-service.service';

describe('AuthenticationServiceService', () => {
  let service: AuthenticatisonService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthenticatisonService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

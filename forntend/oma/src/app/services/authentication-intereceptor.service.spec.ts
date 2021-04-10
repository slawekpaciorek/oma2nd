import { TestBed } from '@angular/core/testing';

import { AuthenticationIntereceptorService } from './authentication-intereceptor.service';

describe('AuthenticationIntereceptorService', () => {
  let service: AuthenticationIntereceptorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthenticationIntereceptorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

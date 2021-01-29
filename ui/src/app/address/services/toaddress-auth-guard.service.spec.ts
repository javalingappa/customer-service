import { TestBed } from '@angular/core/testing';

import { ToaddressAuthGuardService } from './toaddress-auth-guard.service';

describe('ToaddressAuthGuardService', () => {
  let service: ToaddressAuthGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ToaddressAuthGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

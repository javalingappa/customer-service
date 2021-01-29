import { TestBed } from '@angular/core/testing';

import { OptionsAuthGuardService } from './options-auth-guard.service';

describe('OptionsAuthGuardService', () => {
  let service: OptionsAuthGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OptionsAuthGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

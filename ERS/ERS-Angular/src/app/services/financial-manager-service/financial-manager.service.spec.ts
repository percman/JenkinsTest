import { TestBed, inject } from '@angular/core/testing';

import { FinancialManagerService } from './financial-manager.service';

describe('FinancialManagerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FinancialManagerService]
    });
  });

  it('should be created', inject([FinancialManagerService], (service: FinancialManagerService) => {
    expect(service).toBeTruthy();
  }));
});

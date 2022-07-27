import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StartRepartitionComponent } from './start-repartition.component';

describe('StartRepartitionComponent', () => {
  let component: StartRepartitionComponent;
  let fixture: ComponentFixture<StartRepartitionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StartRepartitionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StartRepartitionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

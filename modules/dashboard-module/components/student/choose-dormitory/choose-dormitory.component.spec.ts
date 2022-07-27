import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChooseDormitoryComponent } from './choose-dormitory.component';

describe('ChooseDormitoryComponent', () => {
  let component: ChooseDormitoryComponent;
  let fixture: ComponentFixture<ChooseDormitoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChooseDormitoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChooseDormitoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

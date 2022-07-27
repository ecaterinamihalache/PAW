import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../../services/AdminService';
import { Router } from "@angular/router";
import { Account } from '../../../models/Account';

@Component({
  selector: 'app-first-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {

  public admin: Account

  constructor(private adminManager: AdminService,private router: Router) {
  }

  ngOnInit(): void {
    this.adminManager.getAdminById(Number(localStorage.getItem('accountId'))).subscribe(
      (response) => {
        this.admin = response
      }
    )
  }
  public onLogout():void{
    this.router.navigate(['/']);
    localStorage.clear();
  }
}

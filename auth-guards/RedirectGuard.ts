import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Token } from '../modules/authentication-module/models/Token';
import { catchError, map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { state } from '@angular/animations';
import { UserAccessControlService } from '../modules/authentication-module/services/UserAccessControlService';

@Injectable()
export class RedirectGuard implements CanActivate {
    constructor(private userAccessControl: UserAccessControlService, private router: Router) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {

        let token = localStorage.getItem('token')
        let accountId = localStorage.getItem('accountId')
        let role = localStorage.getItem('role')

        if(role == 'USER')
        {
            this.router.navigate(['/dashboard/student-panel'])
            return false
        }
        else if(role == 'ADMIN')
        {
            this.router.navigate(['/dashboard/admin-panel'])
            return false
        }
        else
        {
            this.router.navigate(['/authentication/login'])
            return false
        }
    }
}
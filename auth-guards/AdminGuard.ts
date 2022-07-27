import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthGuard } from './AuthGuard';

@Injectable()
export class AdminGuard implements CanActivate {
    constructor(private authGuard: AuthGuard, private router: Router) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

        let token = localStorage.getItem('token')
        let accountId = localStorage.getItem('accountId')
        let role = localStorage.getItem('role')

        if(role == 'ADMIN')
        {
            return true
        }
        else
        {
            this.router.navigate(['/unauthorized-access'])
            // this.router.navigate(['/dashboard/student-panel'])
            // this.router.navigate(['/authentication/login'])
            return false
        }

    }
}
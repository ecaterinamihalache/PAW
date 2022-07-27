import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class StudentGuard implements CanActivate {
    constructor(private router: Router) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

        let token = localStorage.getItem('token')
        let accountId = localStorage.getItem('accountId')
        let role = localStorage.getItem('role')

        if(role == 'USER')
        {
            return true
        }
        else
        {
            this.router.navigate(['/unauthorized-access'])
            // this.router.navigate(['/dashboard/admin-panel'])
            // this.router.navigate(['/authentication/login'])
            return false
        }

    }
}
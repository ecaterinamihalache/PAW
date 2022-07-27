import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { UserAccessControlService } from '../modules/authentication-module/services/UserAccessControlService';
import { Token } from '../modules/authentication-module/models/Token';
import { catchError, map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable()
export class AuthGuard implements CanActivate {
    constructor(private userAccessControl: UserAccessControlService, private router: Router) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {

        let token = localStorage.getItem('token')
        let accountId = localStorage.getItem('accountId')
        let role = localStorage.getItem('role')

        if(token == null || accountId == null || role == null)
        {
            if(state.url != "/authentication/login")
            {
                this.router.navigate(['/authentication/login'])
                return false
            }
            else
            {
                return true
            }
        }

        return this.userAccessControl.authorizeUser(new Token(token))
        .pipe(
            map(obj => { 
                
                if(state.url != "/authentication/login")
                {
                    return true
                }
                else
                {
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
                        this.router.navigate(['/unauthorized-access'])
                        return false
                    }
                }
            }), 
            catchError<any, any>(err => 
                (state.url != "/authentication/login")? this.router.navigate(['/authentication/login']) : new Promise((resolve) => { resolve(true); }))          
            );
        }
}
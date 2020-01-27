import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {LoginService} from './login.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private loginService: LoginService,
              private router: Router) {
  }

  /**
   * Возможно ли совершить переход
   * @param next - роут, на который нужно сделать переход
   * @param state
   */
  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    console.log(next.url[0].path == 'calc');
    let result = false;
    if (this.loginService.token) {
      result = true;
    } else {
      this.router.navigate(['/']);
    }
    return result;
  }

}

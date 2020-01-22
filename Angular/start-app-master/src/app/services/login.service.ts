import {Injectable} from '@angular/core';
import {RestService} from './rest.service';
import {map} from 'rxjs/operators';
import {SessionService} from './session.service';
import { Corp } from '../pages/calculation/calculation/calculation.component';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public userName = 'Войти';
  public token;

  constructor(private restService: RestService,
              private sessionService: SessionService) {
    this.userName = sessionService.getSessionParam('userName');
    if (!this.userName) {
      this.userName = 'Войти';
    }
    this.token = sessionService.getSessionParam('token');
  }

  /**
   * При нажатии на кнопку "войти"
   */
  public doLogin(login: string, password: string) {
    console.log('login: ' + login);
    console.log('password: ' + password);
    const params = {
      login: login,
      password: password
    };
    return this.restService.doCall('doLogin', params, '/rest/', 'POST')
      .pipe(
        map((res) => {
          this.userName = res.userName;
          this.token = res.token;
          this.sessionService.setSessionParam('userName', this.userName);
          this.sessionService.setSessionParam('token', this.token);
          console.log('userName', this.userName);
          console.log('token', this.token);
          return res;
        })
      );
  }

  public doRegistration(email: string, password: string, login: string, teacher: boolean, group: string) {
    console.log('login: ' + login);
    console.log('password: ' + password);
    console.log('email: ' + email);
    console.log('teacher: ' + teacher);
    const params = {
      login: login,
      group: group,
      password: password,
      email: email,
      teacher: teacher
    };
    return this.restService.doCall('doRegistration', params, '/rest/', 'POST')
      .pipe(
        map((res) => {
          this.userName = res.userName;
          this.token = res.token;
          this.sessionService.setSessionParam('userName', this.userName);
          this.sessionService.setSessionParam('token', this.token);
          console.log('userName', this.userName);
          console.log('token', this.token);
          return res;
        })
      );
  }
  

}

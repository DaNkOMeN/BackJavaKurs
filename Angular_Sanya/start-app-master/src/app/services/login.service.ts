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
    const params = {
      login: login,
      password: password
    };
    console.log("POST /restdoLogin");
    console.log(params);
    console.log("Ожидается ответ: {'string' : 'userName'"," 'string' : 'token'}");
    return this.restService.doCall('doLogin', params, '/rest/', 'POST')
      .pipe(
        map((res) => {
          this.userName = res.userName;
          this.token = res.token;
          this.sessionService.setSessionParam('userName', this.userName);
          this.sessionService.setSessionParam('token', this.token);
          return res;
        })
      );
  }

  public doRegistration(email: string, password: string, login: string, role: string, en: boolean) {
    const params = {
      login: login,
      password: password,
      email: email,
      role: role,
      isBlock: en
    };
    console.log("POST /rest/login/doRegistration");
    console.log(params);
    console.log("Ожидается ответ: {'string' : 'userName'"," 'string' : 'token'}");
    return this.restService.doCall('doRegistration', params, '/rest/', 'POST')
      .pipe(
        map((res) => {
          this.userName = res.userName;
          this.token = res.token;
          this.sessionService.setSessionParam('userName', this.userName);
          this.sessionService.setSessionParam('token', this.token);
          return res;
        })
      );
  }
  

}

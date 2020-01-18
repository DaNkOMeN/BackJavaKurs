import {Component, OnInit} from '@angular/core';
import {RestService} from '../../services/rest.service';
import {LoginService} from '../../services/login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  /**
   * Имя пользователя
   */
  public login="Войти";
  public email: string;
  public password: string;
  public password2: string;
  public registration = false;
  public teacher = false;
  constructor(private loginService: LoginService,
              private router: Router) {
  }

  ngOnInit() {
  }

  /**
   * При нажатии на кнопку "войти"
   */
  public doLogin() {
    if (this.login){
    if(this.password){
    this.loginService.doLogin(this.email, this.password)
      .subscribe((res: any) => {
        if (res.token) {
          this.router.navigate(['/root']);
        }
      });
  }
}
}

public doRegistration() {
  if (this.login){
  if( this.password == this.password2 ){
  if (this.email)  {
  this.loginService.doRegistration(this.email, this.password, this.login, this.teacher)
    .subscribe((res: any) => {
      if (res.token) {
        this.router.navigate(['/root']);
        }
      });
    }}}
  }
}

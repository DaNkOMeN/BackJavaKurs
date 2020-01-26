import {Component, OnInit} from '@angular/core';
import {RestService} from '../../services/rest.service';
import {LoginService} from '../../services/login.service';
import {Router} from '@angular/router';
import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import {map} from 'rxjs/operators';

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
  public group: string;
  public password: string;
  public password2: string;
  public registration = false;
  public teacher = false;
  public grups;
  public role;
  public err = false;
  public error;
  constructor(private loginService: LoginService,
              private router: Router, private http: HttpClient) {
  }

  ngOnInit() {
    this.load_guide_grups();
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
      }, error => {
        console.log(error);
        this.error = "Не удалось войти "+error.status + " " + error.statusText;
        this.err = true;
      });
  }
}
}

public doRegistration() {
  if (this.login){
    if( this.password == this.password2 ){
      if (this.email)  {
        if (this.teacher) this.role = "3";
        else this.role = "1";
        this.loginService.doRegistration(this.email, this.password, this.login, this.role, true)
          .subscribe((res: any) => {
            if (res.token) {
              this.router.navigate(['/root']);
              }
            }, error => {
              console.log(error);
              this.error = "Не удалось зарегистрироваться "+error.status + " " + error.statusText;
              this.err = true;
            });
          }
        }
      else {
        this.error = "Пароли не совпадают";
        this.err = true;}
      }
  }
  
  load_guide_grups(){
    this.load_grups().subscribe(data => {
    }, (error : HttpErrorResponse) => {
      console.log(error);
      this.error = " Не удалось загрузить список групп " + error.status + " " + error.statusText;
      this.err = true;
    });
  }

  load_grups(){
    const endpoint = '/rest/loadGrups';
    console.log("GET, /rest/loadGrups ")
    console.log("Ожидается ответ: {[{id, title, enable}{id, title, enable}... ]}");
    return this.http

    .get(endpoint, { headers: new HttpHeaders({'Content-Type': 'application/json; charset=UTF-8'})})
    .pipe(map((res:any) => { 
      this.grups = res;
      console.log("Загружен Группы");
      console.log(this.grups);
      return true; }));
  }


}


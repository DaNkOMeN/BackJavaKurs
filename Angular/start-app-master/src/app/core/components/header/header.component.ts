import {Component, Input, OnInit} from '@angular/core';
import {LoginService} from '../../../services/login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  userName = 'Войти';

  constructor(public loginService: LoginService, private router: Router) {
  }

  ngOnInit() {
  }

    route(){
      this.router.navigate(['/root']);
  }
}

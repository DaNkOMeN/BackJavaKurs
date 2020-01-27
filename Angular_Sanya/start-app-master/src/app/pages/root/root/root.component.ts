import {Component, OnInit} from '@angular/core';
import {SessionService} from '../../../services/session.service';
import { DataTableDirective } from 'angular-datatables';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { map } from 'rxjs/internal/operators/map';

@Component({
  selector: 'app-root',
  templateUrl: './root.component.html',
  styleUrls: ['./root.component.scss']
})

export class RootComponent implements OnInit {
 
  public tempArr = null;
  public userName = 'NoName';
  private Fill =  false;
  private Comp = false;
  private Proj = false;
  private Teacher = this.userName;
  private group;
  private cabinet;
  private para = 0;
  private corp = 0;
  private day = 0;
  private kabs;
  public grups;
  public dtable;
  public request;
  public reason;
  public corps;
  public showWeeks : boolean = false;
  private weeks: boolean[] = [false, false, false, false, false, false, false, false, false, 
                              false, false, false, false, false, false, false, false, false];

  public checkboxModel: any = { left: true, middle: false, right: false };

  constructor(private SessionService: SessionService,  private http: HttpClient) {
    this.userName = this.SessionService.getSessionParam('userName');
  }
  
  private lessons;

  ngOnInit() {
    console.log("Подгружаем справочники");
    this.load_guide_grups();
    this.load_guide_kabs();
    this.load_guide_corps();
    this.load_teachers();
  }

  AutoReload_pars()
  {
    this.dtable = $('table_id1').DataTable();
    this.dtable.clear();
    this.lessons.forEach(element => {
      this.dtable.row.add([element.week, element.day, element.pair, element.kab.corp+" "+element.kab.number, 
      element.comp, element.proj, element.busy, element.discription]);
    });
    this.dtable.draw();
  }

  change_rasp(action: string){
    this.toTable_pairs(action).subscribe(data => {
    }, error => {
      console.log(error);
    });
  }

  toTable_pairs(action: string){
    const endpoint = "/rest/lessons";
    this.request = {
      action: action,
      busy: this.Fill,
      comp: this.Comp,
      proj: this.Proj,
      corp_id: this.corp,
      kabinet_id: this.cabinet,
      group_id: this.group,
      teacher_id: this.Teacher,
      pair: this.para,
      day: this.day,
      weeks: this.weeks,
      reason: this.reason
  };
  console.log("POST /rest/lessons")
  console.log(this.request);
  console.log("Ожидается ответ: {[{id, login, email, password, grup, role, enable}{id, login, email, password, grup, role, enable}... ]}");
  return this.http
  .post(endpoint, this.request, { headers: new HttpHeaders({'Content-Type': 'application/json; charset=UTF-8'})})
  .pipe(map((res:any) => { 
    this.lessons = res;
    console.log("Загружен пользователи");
    console.log(this.lessons);
    this.AutoReload_pars();
    return true; 
  }));
  }


getSelectedValues(event: any) {
  console.log('Selected value', event);
  }

  NeChetnie(){
    for (let index = 0; index <= 9; index++) {
      this.weeks[index * 2] = true;
    }
    for (let index = 1; index <= 9; index++) {
      this.weeks[index * 2 - 1] = false;
    }
  }

  Chetnie(){
    for (let index = 0; index <= 9; index++) {
      this.weeks[index * 2] = false;
    }
    for (let index = 1; index <= 9; index++) {
      this.weeks[index * 2 - 1] = true;
    }
  }

  all(){
    for (let index = 0; index <= 18; index++) {
      this.weeks[index] = true;
    }
  }

  never(){
    for (let index = 0; index <= 18; index++) {
      this.weeks[index] = false;
    }
  }

  load_guide_grups(){
    this.load_grups().subscribe(data => {
    }, error => {
      console.log(error);
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
      this.group = 0;
      return true; }));
  }

  load_guide_kabs(){
    this.load_kabs().subscribe(data => {
    }, error => {
      console.log(error);
    });
  }
  load_kabs(){
      const endpoint = 'rest/loadCabs';
      console.log("GET rest/loadCabs")
      console.log("Ожидается ответ: {[ {id, corp, number, comp, proj, enable}{id, corp, number, comp, proj, enable}... ]}");
      return this.http
      .get(endpoint, { headers: new HttpHeaders({'Content-Type': 'application/json; charset=UTF-8'})})
      .pipe(map((res:any) => { 
        this.kabs = res;
        console.log("Загружен кабинеты");
        console.log(this.kabs);
        return true; }));
    }
    load_guide_corps(){
      this.load_corps().subscribe(data => {
      }, error => {
        console.log(error);
      });
    }
  
    load_corps(){
      const endpoint = '/rest/loadCorps';
      console.log("GET, /rest/loadCorps")
      console.log("Ожидается ответ: {[ {id, title, enable}{id, title, enable}... ]}");
      return this.http
  
      .get(endpoint, { headers: new HttpHeaders({'Content-Type': 'application/json; charset=UTF-8'})})
      .pipe(map((res:any) => { 
        this.corps = res;
        console.log("Загружен корпуса");
        console.log(this.corps);
        return true; }));
    }
  
    load_teachers(){
      this.load_teacher().subscribe(data => {
      }, error => {
        console.log(error);
      });
    }
  
    load_teacher(){
      const endpoint = '/rest/loadTeachers';
      console.log("GET, /rest/loadTeachers")
      console.log("Ожидается ответ: {[{id, name}{id, name}... ]}");
      return this.http
      .get(endpoint, { headers: new HttpHeaders({'Content-Type': 'application/json; charset=UTF-8'})})
      .pipe(map((res:any) => { 
        this.corps = res;
        console.log("Загружен корпуса");
        console.log(this.corps);
        return true; }));
    }
  
}
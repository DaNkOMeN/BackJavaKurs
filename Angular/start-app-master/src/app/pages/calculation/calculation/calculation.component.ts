import { Component, OnInit } from '@angular/core';
import { RestService } from 'src/app/services/rest.service';
import {map} from 'rxjs/operators';
import { LoginService } from 'src/app/services/login.service';
import { Observable } from 'rxjs';
import { HttpEvent, HttpParams, HttpRequest, HttpClient, HttpHeaders } from '@angular/common/http';
import { DataTableDirective } from 'angular-datatables';

@Component({
  selector: 'app-calculation',
  templateUrl: './calculation.component.html',
  styleUrls: ['./calculation.component.scss']
})
export class CalculationComponent implements OnInit {

  public corps: any[];
  public grups: any[];
  public users: any[];
  public red_corp = [
    {
      action: "nothing",
      corp: {
        id:0,
        numberOfHousing: "",
        enable: false
      }
    }
  ];
  public kabs = [
    {
      id: 0,
      corp: 2,
      number: 113,
      comp: true,
      proj: false,
      enable: true
    },
    {
      id: 1,
      housingNumber: 1,
      number: 114,
      computer: false,
      projector: false,
      enable: true
    }
  ]
  public red_grup = [
    {
      action: "nothing",
      groups: {
        id:0,
        nameOfGroup: "",
        enable: false
      }
    }
  ];
  public red_corp_action_count = 0;
  public red_grup_action_count = 0;
  public current_corp;
  public current_grup;
  public current_user;
  public cur_corp_en = false;
  public cur_grup_en = false;
  public cur_user_en = false;
  public cur_cor_title;
  public cur_grup_title;
  public red_user;
  public cur_user_login;
  public cur_user_pass;
  public cur_user_email;
  public cur_user_role = 1;
  public cur_user_grup = 0;
  public current_kab;
  public cur_corp_for_cab;
  public cabinet_number;
  public cur_kab_comp;
  public cur_kab_proj;
  public cur_kab_en;
  public reqF: boolean;
  public corps_is_load = false;
  public grups_is_load = false;
  public users_is_load = false;
  public kabs_is_load = false;
  fileToUpload: File = null;

  constructor(private restService: RestService, private loginService: LoginService, private http: HttpClient) {
  }

  ngOnInit() {
  }


  handleFileInput(files: FileList) {
      this.fileToUpload = files.item(0);
  }

  uploadFileToActivity() {
      this.postFile(this.fileToUpload).subscribe(data => {
        }, error => {
          console.log(error);
        });
    }

  postFile(fileToUpload: File): Observable<boolean> {
      const endpoint = '/rest/main/uploadFile';
      const formData: FormData = new FormData();
      console.log("File отправлен");
      formData.append('uploadedFile', fileToUpload);
      return this.http
      .post(endpoint, formData, { headers: new HttpHeaders({})})
      .pipe(map((res) => {
        console.log("УСпех");
        return true; }));
    }

  load_guide_corps(t : boolean){
    this.load_corps(t).subscribe(data => {
    }, error => {
      console.log(error);
    });
  }

  load_corps(t : boolean){
    const endpoint = '/rest/main/getAllHoustings';
    console.log("GET, /rest/main/getAllHoustings")
    console.log("Ожидается ответ: {[ {id, numberOfHousing, enable}{id, numberOfHousing, enable}... ]}");
    return this.http

    .get(endpoint, { headers: new HttpHeaders({'Content-Type': 'application/json; charset=UTF-8'})})
    .pipe(map((res:any) => {
      this.corps = res;
      console.log("Загружен корпуса");
      console.log(this.corps);
	  this.current_corp = this.corps[0].id;
	  this.cur_corp_for_cab = this.corps[0].id;
      this.corps_is_load = t;
      this. AutoReload_corps();
      return true; }));
  }

  save_guide_corps(){
    this.save_corps().subscribe(data => {
    }, error => {
      console.log(error);
    });
  }

  save_corps(){
    const endpoint = '/rest/main/updateCorps';
    const param =  this.red_corp
    console.log("POST, /rest/main/updateCorps");
    console.log(param);
    console.log("Ожидается ответ: {[ {id, numberOfHousing, enable}{id, numberOfHousing, enable}... ]}");
    return this.http
    .post(endpoint, param, { headers: new HttpHeaders({'Content-Type': 'application/json; charset=UTF-8'})})
    .pipe(map((res:any) => {
      this.corps = res;
      console.log("Ответ на POST");
      console.log(this.corps);
      this.red_corp = [
        {
          action: "nothing",
          corp: {
            id:0,
            numberOfHousing: "",
            enable: false
          }
        }
      ];;
      console.log(this.red_corp);
      this. AutoReload_corps();
      return true; }));
  }

private i;
private m;
  save_toTable_corps(){
	 this.i = 0;
	this.corps.forEach(element =>
	{
		if (element.id == this.current_corp) this.m = this.i;
		this.i++;
	});
    this.corps[this.m] = {
      id: this.current_corp,
      numberOfHousing: this.cur_cor_title,
      enable: this.cur_corp_en
    }
    this.red_corp[this.red_corp_action_count] = {
      action: "nothing",
      corp: {
        id:0,
        numberOfHousing: "",
        enable: false
      }
    }
    this.red_corp.length++;
    this.red_corp[this.red_corp_action_count].action = "Редактирование";
    this.red_corp[this.red_corp_action_count].corp = this.corps[this.m];
    this.red_corp_action_count++;
    console.log(this.corps);
    this.AutoReload_corps();
  }

  add_toTable_corps(){
    this.corps.length = this.corps.length+1;
    this.corps[this.corps.length-1] = {
      id: this.corps.length-1,
      numberOfHousing: this.cur_cor_title,
      enable:this.cur_corp_en
    }
    this.red_corp[this.red_corp_action_count] = {
      action: "nothing",
      corp: {
        id:0,
        numberOfHousing: "",
        enable: false
      }
    }
    this.red_corp[this.red_corp_action_count].action = "Добавление";
    this.red_corp[this.red_corp_action_count].corp = this.corps[this.corps.length-1];
    this.red_corp_action_count++;
    this.red_corp.length++;
    console.log(this.corps);
    this. AutoReload_corps();
  }

  load_guide_grups(t: boolean){
    this.load_grups(t).subscribe(data => {
    }, error => {
      console.log(error);
    });
  }

  load_grups(t: boolean){
    const endpoint = '/rest/main/getAllGroups';
    console.log("GET, /rest/main/getAllGroups")
    console.log("Ожидается ответ: {[{id, nameOfGroups, enable}{id, title, enable}... ]}");
    return this.http.get(endpoint, { headers: new HttpHeaders({'Content-Type': 'application/json; charset=UTF-8'})})
    .pipe(map((res:any) => {
      this.grups = res;
      console.log("Загружен Группы");
      console.log(this.grups);
      this.grups_is_load = t;
      this. AutoReload_grups();
      return true; }));
  }

  save_guide_grups(){
    this.save_grups().subscribe(data => {
    }, error => {
      console.log(error);
    });
  }

  save_grups(){
    const endpoint = '/rest/main/updateGroups';
    const param = this.red_grup;
    console.log("POST /rest/main/updateGroups, ");
    console.log(param);
    console.log("Ожидается ответ: {[ {id, nameOfGroup, enable}{id, nameOfGroup, enable}... ]}");
    return this.http
    .post(endpoint, param, { headers: new HttpHeaders({'Content-Type': 'application/json; charset=UTF-8'})})
    .pipe(map((res:any) => {
      this.grups = res;
      console.log("Ответ группы");
      console.log(this.grups);
      this.red_grup = [
        {
          action: "nothing",
          groups: {
            id:0,
            nameOfGroup: "",
            enable: false
          }
        }
      ];
      this.AutoReload_grups();
      return true; }));
  }

  save_toTable_grups(){
    this.i = 0;
    this.grups.forEach(element =>
    {
      if (element.id == this.current_grup) this.m = this.i;
      this.i++;
    });
    this.grups[this.m] = {
      id: this.current_grup,
      nameOfGroup: this.cur_grup_title,
      enable: this.cur_grup_en
    }
    this.red_grup[this.red_grup_action_count] = {
      action: "nothing",
      groups: {
        id:0,
        nameOfGroup: "",
        enable: false
      }
    }
    this.red_grup.length++;
    this.red_grup[this.red_grup_action_count].action = "Редактирование";
    this.red_grup[this.red_grup_action_count].groups = this.grups[this.m];
    this.red_grup_action_count++;
    this.AutoReload_grups();
  }

  add_toTable_grups(){
    this.grups.length = this.grups.length+1;
    this.grups[this.grups.length-1] = {
      id: this.grups.length-1,
      nameOfGroup: this.cur_grup_title,
      enable:this.cur_grup_en
    }
    this.red_grup[this.red_grup_action_count] = {
      action: "nothing",
      groups: {
        id:0,
        nameOfGroup: "",
        enable: false
      }
    }
    this.red_grup[this.red_grup_action_count].action = "Добавление";
    this.red_grup[this.red_grup_action_count].groups = this.grups[this.grups.length-1];
    this.red_grup_action_count++;
    this.red_grup.length++;
    this.AutoReload_grups();
  }

  private dtable : DataTables.Api;

  AutoReload_corps()
  {
    this.dtable = $('#table_id1').DataTable();
    this.dtable.clear();
    this.corps.forEach(element => {
      this.dtable.row.add([element.id, element.numberOfHousing, element.enable]);
    });
    this.dtable.draw();
  }

  AutoReload_grups()
  {
    this.dtable = $('#table_id2').DataTable();
    this.dtable.clear();
    this.grups.forEach(element => {
      this.dtable.row.add([element.id, element.nameOfGroup, element.enable]);
    });
    this.dtable.draw();
  }

  load_guide_users(){
    const t = this.grups_is_load;
    this.load_guide_grups(t);
    this.load_users().subscribe(data => {
    }, error => {
      console.log(error);
    });

  }

  load_users(){
    const endpoint = 'rest/main/getAllUsers';
    console.log("GET rest/main/getAllUsers")
    console.log("Ожидается ответ: [ {id, login, email, password, role, enable}{id, login, email, password, role, enable}... ]");
    return this.http

    .get(endpoint, { headers: new HttpHeaders({'Content-Type': 'application/json; charset=UTF-8'})})
    .pipe(map((res:any) => {
      this.users = res;
      console.log("Загружен пользователи");
      console.log(this.users);
	  this.current_user = this.users[0].id;
      this.users_is_load = true;
      this. AutoReload_users();
      return true; }));
  }
  AutoReload_users()
  {
    this.dtable = $('#table_id3').DataTable();
    this.dtable.clear();
    this.users.forEach(element => {
      this.dtable.row.add([element.id, element.login, element.email, element.password, element.role, element.block]);
    });
    this.dtable.draw();
  }

  save_toTable_users(){
    this.toTable_users().subscribe(data => {
    }, error => {
      console.log(error);
    });
  }

  toTable_users(){
    const endpoint = "/rest/main/updateUser";

    this.red_user = {
      id: this.current_user,
      login: this.cur_user_login,
      password: this.cur_user_pass,
      email: this.cur_user_email,
      role: this.cur_user_role,
      enable: this.cur_user_en
  };

  console.log("POST /rest/changeRegistration")
  console.log(this.red_user);
  console.log("Ожидается ответ: {[{id, login, email, password, grup, role, enable}{id, login, email, password, grup, role, enable}... ]}");
  return this.http
  .post(endpoint, this.red_user, { headers: new HttpHeaders({'Content-Type': 'application/json; charset=UTF-8'})})
  .pipe(map((res:any) => {
    this.users = res;
    console.log("Загружен пользователи");
    console.log(this.users);
    this.users_is_load = true;
    this. AutoReload_users();
    return true;
  }));
  }

  add_toTable_users(){
    this.loginService.doRegistration(this.cur_user_email, this.cur_user_pass,
      this.cur_user_login, this.cur_user_role.toString(), this.cur_user_en)
    .subscribe((res: any) => {
      if (res.token) {
      console.log("Успех");
      this.load_guide_users();
      this.load_users;}
      }, error => {
        console.log(error);
      });
  }
load_guide_kabs(){
  const t = this.corps_is_load;
  this.load_guide_corps(t);
  this.load_kabs().subscribe(data => {
  }, error => {
    console.log(error);
  });
}
load_kabs(){
    const endpoint = 'rest/main/getAllAuditories';
    console.log("GET rest/main/getAllAuditories")
    console.log("Ожидается ответ: {[ {id, corp, number, comp, proj, enable}{id, corp, number, comp, proj, enable}... ]}");
    return this.http
    .get(endpoint, { headers: new HttpHeaders({'Content-Type': 'application/json; charset=UTF-8'})})
    .pipe(map((res:any) => {
      this.kabs = res;
      console.log("Загружен кабинеты");
      console.log(this.kabs);
      this.cur_kab_comp = true;
      this.cur_kab_proj = true;
      this.cur_kab_en = true;
      this.current_kab = this.kabs[0].id;

      this.kabs_is_load = true;
      this.AutoReload_kabs();
      return true; }));
  }
AutoReload_kabs(){
  this.dtable = $('#table_id4').DataTable();
  this.dtable.clear();
  this.kabs.forEach(element => {
    this.dtable.row.add([element.id, element.number, element.housingNumber, element.computer, element.projector, element.enable]);
  });
  this.dtable.draw();
}

save_toTable_kabs(act: string){
  this.save_kabs(act).subscribe(data => {
  }, error => {
    console.log(error);
  });
}

save_kabs(act: string){
  const endpoint = 'rest/main/updateAuditories';
  console.log("POST rest/main/updateAuditories")
  const param = {
    action: act,
    housingId: this.cur_corp_for_cab,
    auditory: {
      id: this.current_kab,
      number: this.cabinet_number,
      computer: this.cur_kab_comp,
      projector: this.cur_kab_proj,
      enable: this.cur_kab_en
    }
  }
  console.log(param);
  console.log("Ожидается ответ: {[ {id, corp, number, comp, proj, enable}{id, corp, number, comp, proj, enable}... ]}");
  return this.http
  .post(endpoint, param, { headers: new HttpHeaders({'Content-Type': 'application/json; charset=UTF-8'})})
  .pipe(map((res:any) => {
    this.kabs = res;
    console.log("Загружен кабинеты");
    console.log(this.kabs);
    this.kabs_is_load = true;
    this.AutoReload_kabs();
    return true; }));
}

}


import { Component, OnInit } from '@angular/core';
import { RestService } from 'src/app/services/rest.service';
import {map} from 'rxjs/operators';
import { LoginService } from 'src/app/services/login.service';
import { Observable } from 'rxjs';
import { HttpEvent, HttpParams, HttpRequest, HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-calculation',
  templateUrl: './calculation.component.html',
  styleUrls: ['./calculation.component.scss']
})
export class CalculationComponent implements OnInit {
  
  public newCorp: string;
  public guides_ = [{
    id:0,
    name: "NoName",
    lines:[{enable: true, id: 0, title: "true"}, {enable: true, id: 0, title: "false"}]
  }];
  public guides_name;
  public guides_index = 1;
  private corpsCount:number = this.guides_[this.guides_index-1].lines.length-1;
  public request_prepod;
  public reqF: boolean;
  fileToUpload: File = null;
  constructor(private restService: RestService, private loginService: LoginService, private http: HttpClient) {
    this.getCorps();
    this.corpsCount = this.guides_[this.guides_index-1].lines.length-1;
  }

  ngOnInit() {
  }

  addCorp(){
    this.corpsCount = this.guides_[this.guides_index-1].lines.length-1;
    const tempCorp:Corp = {
      id: this.guides_[this.guides_index-1].lines.length,
      title: "Новый элемент",
      enable: true
    };
    //this.guides_[this.guides_index-1].lines.length = this.guides_[this.guides_index-1].lines.length + 1;
    this.guides_[this.guides_index-1].lines[this.corpsCount+1]= tempCorp;
    this.corpsCount = this.guides_[this.guides_index-1].lines.length-1;
    console.log(this.guides_);
  }

  req_accepted(){
    this.req_acc(true).subscribe((res: any) => {});
  }

  req_dicline(){
    this.req_acc(false).subscribe((res: any) => {});
  }

  req_load(){
    this.reqload().subscribe((res: any) => {});
  }

  handleFileInput(files: FileList) {
    this.fileToUpload = files.item(0);
}

uploadFileToActivity() {
    this.postFile(this.fileToUpload).subscribe(data => {
      // do something, if upload success
      }, error => {
        console.log(error);
      });
  }
  postFile(fileToUpload: File): Observable<boolean> {
    const endpoint = '/rest/uploadFile';
    const formData: FormData = new FormData();
    
    formData.append('uploadedFile', fileToUpload);
    return this.http
    .post(endpoint, formData, { headers: new HttpHeaders({})})
    .pipe(map((res) => { return true; }));
  }

  reqload(){
    return this.restService.doCall('teachersReq', null, '/rest/', 'GET')
      .pipe(
        map((res) => {
          if (res) this.request_prepod = res;
          else this.reqF=false;
          console.log(res);
          return res;
        })
      );
  }

  req_acc(option: boolean){
    const params = {
      login: this.request_prepod.login,
      option: option
    }
    console.log(params);
    return this.restService.doCall('teachersReq', params, '/rest/', 'POST')
      .pipe(
        map((res) => {
          if (res) this.request_prepod = res;
          else this.reqF=false;
          console.log(res);
          return res;
        })
      );
  }

  removeCorp(){
    this.guides_[this.guides_index-1].lines[this.corpsCount].enable = false;
    console.log(this.guides_[this.guides_index-1].lines);
  }


  public getCorps() {
    this.loadCorps().subscribe((res: any) => {});
  }

  public upCorps() {
    this.upDateCorps().subscribe((res: any) => {});
  }

public upDateCorps() {
  console.log('Обновляем корпуса');
  const params = {
    guideName: this.guides_[this.guides_index-1].name,
    lines: this.guides_[this.guides_index-1].lines
  };
  console.log(params);
  return this.restService.doCall('updateCorps', params, '/rest/', 'POST')
    .pipe(
      map((res) => {
        this.guides_[this.guides_index-1].lines = res;
        console.log(res);
        return res;
      })
    );
}

public loadCorps() {
  console.log('Просим корпуса');
  return this.restService.doCall('updateCorps', null, '/rest/', 'GET')
    .pipe(
      map((res) => {
        this.guides_ = res;
        console.log(res);
        return res;
      })
    );
}

}
export class Corp{
  id: number;
  title: string;
  enable: boolean
}

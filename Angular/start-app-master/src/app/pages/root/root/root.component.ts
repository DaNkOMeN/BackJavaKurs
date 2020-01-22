import {Component, OnInit} from '@angular/core';
import {SessionService} from '../../../services/session.service';
import { DataTableDirective } from 'angular-datatables';

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
  private Group = this.userName;
  private cabinet = 101;
  private para = 0;
  private corp = 0;
  private day = 0;
  private les: Lesson;
  private t : boolean;
  private e : boolean;
  private m : boolean;
  public showWeeks : boolean = false;
  private weeks: boolean[] = [false, false, false, false, false, false, false, false, false, 
                              false, false, false, false, false, false, false, false, false];

  public checkboxModel: any = { left: true, middle: false, right: false };

  constructor(private SessionService: SessionService) {
    this.userName = this.SessionService.getSessionParam('userName');
  }
  
  private lessons: Lesson[] = [];

  ngOnInit() {
    
      for (let index = 0; index < 150; index++) {
        this.t = false;
        this.e = false;
        if (index%2 == 1) this.t = true;
        if (index%4 == 3) this.e = true;
        this.les = {
          Week: index%18 + 1,
          Day: (index%7 + 1).toString(),
          Para: index%7 + 1,
          Building_Cab: index.toString(),
          Comp: this.t,
          Proj: this.e,
          Teacher_Group: index.toString(),
          Dopno: index.toString()
        };
        this.lessons[index]=this.les;
        //console.log('Selected value', this.lessons);
      }
  }

getSelectedValues(event: any) {
  console.log('Selected value', event);
  }

  Console(){
    console.log(" "+this.Fill+
                " "+this.Comp+
                " "+this.Proj+
                " "+this.weeks+
                " "+this.Teacher+
                " "+this.Group+
                " "+this.cabinet+
                " "+this.para+
                " "+this.corp+
                " "+this.day
                );
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
}

export class Lesson {
  Week: number;
  Day: string;
  Para: number;
  Building_Cab: string;
  Comp: boolean;
  Proj: boolean;
  Teacher_Group: string;
  Dopno: string;
}
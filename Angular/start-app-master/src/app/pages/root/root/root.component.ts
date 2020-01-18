import {Component, OnInit} from '@angular/core';
import {SessionService} from '../../../services/session.service';

@Component({
  selector: 'app-root',
  templateUrl: './root.component.html',
  styleUrls: ['./root.component.scss']
})
export class RootComponent implements OnInit {

  /**
   * \Массив, который нужно вывести
   */
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
  private weeks: boolean[] = [false, false, false, false, false, false, false, false, false, 
                              false, false, false, false, false, false, false, false, false];

  public checkboxModel: any = { left: true, middle: false, right: false };

  constructor(private SessionService: SessionService) {
    this.userName = this.SessionService.getSessionParam('userName');
  }

  ngOnInit() {
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

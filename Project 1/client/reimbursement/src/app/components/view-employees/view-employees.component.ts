import { Component, OnInit } from '@angular/core';
import {ViewEmployeesService} from '../services/view-employees';
import {UpdateService} from '../services/info-update.service';
import {Router} from '@angular/router';
import {Globals} from '../../shared/Globals';

@Component({
  selector: 'app-view-employees',
  providers: [ViewEmployeesService],
  templateUrl: './view-employees.component.html',
  styleUrls: ['./view-employees.component.css']
})
export class ViewEmployeesComponent implements OnInit {

  listing: string[];

  constructor(private viewThem: ViewEmployeesService, public globals: Globals, private router: Router) {}

  doSomething(): void {
    console.log('DOING SOMETHING');
    this.viewThem.viewEmployees().subscribe(success => this.listing = success, err => this.listing = ['WOOPS'] );
    console.log('DID SOMETHING');
  }

  ngOnInit() {
    this.doSomething();
  }

}

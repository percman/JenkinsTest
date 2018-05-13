import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Globals} from '../../shared/Globals';

@Component({
  selector: 'app-employee-splash',
  templateUrl: './employee-splash.component.html',
  styleUrls: ['./employee-splash.component.css']
})
export class EmployeeSplashComponent implements OnInit {

  constructor(public globals: Globals, private router: Router) { }

  ngOnInit() {
  }

}

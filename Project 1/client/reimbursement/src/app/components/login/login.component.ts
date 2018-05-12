import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = '';
  password = '';
  indicator = '';
  constructor() { }

  onSubmit(): void {
    this.indicator = 'IT INDICATED';
  }
  ngOnInit() {
  }

}

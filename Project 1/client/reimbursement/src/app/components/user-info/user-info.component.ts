import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Globals} from '../../shared/Globals';
import {LoginService} from '../services/login.service';
import {CreateReimbursementService} from '../services/create-reimbursement.service';
import {UpdateService} from '../services/info-update.service';
import {Employee} from '../../shared/Employee';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  providers: [UpdateService],
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  indicator = '';

  constructor(private infoUpdateService: UpdateService, public globals: Globals, private router: Router) { }

  onSubmit(): void {
    console.log(this.globals);
    this.infoUpdateService.update({'username': this.globals.username, 'password': this.globals.password, 'address': this.globals.address,
    'firstName': this.globals.firstName, 'lastName': this.globals.lastName, 'eid': this.globals.eid, 'isManager': this.globals.isManager})
      .subscribe(success => this.indicator = success ? 'Success!' : 'Add Failed',
        err => this.indicator = err['error']);
  }

  ngOnInit() {
  }

}

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
    this.infoUpdateService.update(new Employee(this.globals.username, this.globals.password, this.globals.eid, this.globals.isManager,
      this.globals.firstName, this.globals.lastName, this.globals.address))
      .subscribe(success => this.indicator = success ? 'Success!' : 'Add Failed',
        err => this.indicator = err['error']);
  }

  ngOnInit() {
  }

}

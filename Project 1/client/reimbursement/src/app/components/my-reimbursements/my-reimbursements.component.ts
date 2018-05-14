import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {ViewEmployeesService} from '../services/view-employees';
import {Globals} from '../../shared/Globals';
import {GetMyReimbursementsService} from '../services/get-my-reimbursements';
import {MyReimbursement} from '../../shared/MyReimbursement';

@Component({
  selector: 'app-my-reimbursements',
  templateUrl: './my-reimbursements.component.html',
  providers: [GetMyReimbursementsService],
  styleUrls: ['./my-reimbursements.component.css']
})
export class MyReimbursementsComponent implements OnInit {

  listing: MyReimbursement[];

  constructor(private getThem: GetMyReimbursementsService, public globals: Globals, private router: Router) {}

  doSomething(): void {
    this.getThem.getMyReimbursements({'username': this.globals.username, 'password': this.globals.password})
      .subscribe(success => this.listing = success, err => this.listing = []);
  }

  categoryToString(n: number): string {
    switch (n) {
      case 0:
        return 'Lodging';
      case 1:
        return 'Travel';
      case 2:
        return 'Food';
      default:
        return 'Other';
    }
  }

  statusToString(n: number): string {
    switch (n) {
      case 0:
        return 'Pending';
      case 1:
        return 'Approved';
      case 2:
        return 'Denied';
      default:
        return 'Unknown';
    }
  }

  ngOnInit() {
    this.doSomething();
  }
}

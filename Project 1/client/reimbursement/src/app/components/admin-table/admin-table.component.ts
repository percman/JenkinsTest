import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Globals} from '../../shared/Globals';
import {GetMyReimbursementsService} from '../services/get-my-reimbursements';
import {BigReimbursement} from '../../shared/BigReimbursement';
import {AdminTableService} from '../services/admin-table.service';

@Component({
  selector: 'app-admin-table',
  templateUrl: './admin-table.component.html',
  providers: [AdminTableService],
  styleUrls: ['./admin-table.component.css']
})
export class AdminTableComponent implements OnInit {

  listing: BigReimbursement[];

  constructor(private getThem: AdminTableService, public globals: Globals, private router: Router) {}

  doSomething(): void {
    this.getThem.getAllReimbursements({'username': this.globals.username, 'password': this.globals.password})
      .subscribe(success => this.listing = success, err => this.listing = []);
  }

  approve(rid: number): void {
    console.log('approve ' + rid);
    this.getThem.setRStatus({'rid': rid, 'status': 1, 'approver': this.globals.eid})
      .subscribe(success => this.doSomething()
                      , err => this.doSomething());
  }

  deny(rid: number): void {
    console.log('deny ' + rid);
    this.getThem.setRStatus({'rid': rid, 'status': 2, 'approver': this.globals.eid})
      .subscribe(success => this.doSomething()
        , err => this.doSomething());
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

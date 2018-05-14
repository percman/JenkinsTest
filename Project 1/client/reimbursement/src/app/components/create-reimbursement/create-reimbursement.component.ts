import { Component, OnInit } from '@angular/core';
import {CreateReimbursementService} from '../services/create-reimbursement.service';
import {Globals} from '../../shared/Globals';
import {Router} from '@angular/router';
import {ReimbursementForm} from '../../shared/ReimbursementForm';

@Component({
  selector: 'app-create-reimbursement',
  templateUrl: './create-reimbursement.component.html',
  providers: [CreateReimbursementService],
  styleUrls: ['./create-reimbursement.component.css']
})
export class CreateReimbursementComponent implements OnInit {
  category: number;
  amount: number;
  indicator = '';

  constructor(private rs: CreateReimbursementService, public globals: Globals, private router: Router) { }

  onSubmit(): void {
    const rForm = new ReimbursementForm(this.amount, this.category,
                      this.globals.username, this.globals.password);
    this.rs.createReimbursement(rForm).subscribe(success => this.indicator = success ? 'Success!' : 'Add Failed',
      err => this.indicator = err['error']);
  }


  ngOnInit() {
  }

}

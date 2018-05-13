import {Component, OnInit} from '@angular/core';
import {Globals} from './shared/Globals';
import {LoginService} from './components/services/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';
  photo = 'https://strollingfellow.github.io/VeniceResized.jpg';

  constructor(public globals: Globals) { }

  ngOnInit() {
    document.body.classList.add('bg-img');
  }
}

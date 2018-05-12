import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';
  photo = 'https://strollingfellow.github.io/VeniceResized.jpg';

  ngOnInit() {
    document.body.classList.add('bg-img');
  }
}

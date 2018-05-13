import { Component, OnInit } from '@angular/core';
import { ExampleService } from '../../services/example-service/example.service';
import { Post } from '../../../shared/post';

@Component({
  selector: 'app-http-example',
  templateUrl: './http-example.component.html',
  styleUrls: ['./http-example.component.css']
})
export class HttpExampleComponent implements OnInit {

  private errorMessage: string;
  posts: Post[];
  isLoaded: boolean = false;

  constructor(private exampleService: ExampleService) { }

  ngOnInit() {
  }

  getPosts() {
    this.exampleService.getPosts()
                       .subscribe(
                         // Function when asynchronous operation is successful
                         allPosts => this.posts = allPosts,

                         // Function when asynchronous operation is not successful
                         err => this.errorMessage = err
                       );
      this.isLoaded = true;
  }

}

import { Observable, map } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/core/interfaces/post.interface';
import { UserService } from '../../services/user.service';
import { PostService } from '../../services/post.service';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.scss'],
})
export class PostListComponent implements OnInit {
  public posts$!: Observable<Post[]>;
  public gridColumnNumber!: number;
  public sortAsc: boolean = true;

  constructor(
    private postService: PostService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    // Fetch the posts
    this.fetchPosts();
    // Set the grid number of column
    this.setGridColumNumber();
  }

  /**
   * Sets the number of grid columns based on the window width.
   */
  public setGridColumNumber() {
    this.gridColumnNumber = window.innerWidth <= 768 ? 1 : 2;
  }

  public onResize(event: Event): void {
    // Set the grid number of column
    this.setGridColumNumber();
  }

  /**
   * Fetches posts and sorts them based on the `createdDate` property.
   */
  public fetchPosts() {
    this.posts$ = this.postService.getAllPosts().pipe(
      map((posts) => {
        return posts.sort((a, b) => {
          // Convert the `createdDate` properties to Date objects, handling undefined values
          const dateA = a.createdDate ? new Date(a.createdDate) : new Date(0);
          const dateB = b.createdDate ? new Date(b.createdDate) : new Date(0);

          // Check if sorting should be done in ascending or descending order
          if (this.sortAsc) {
            // Sort in ascending order based on the timestamp of the `createdDate`
            return dateA.getTime() - dateB.getTime();
          } else {
            // Sort in descending order based on the timestamp of the `createdDate`
            return dateB.getTime() - dateA.getTime();
          }
        });
      })
    );
  }

  /**
   * Toggles the sorting order and fetches the posts accordingly.
   */
  public toggleSwitch() {
    // Toggle the sorting order flag
    this.sortAsc = !this.sortAsc;

    // Fetch posts based on the updated sorting order
    this.fetchPosts();
  }
}

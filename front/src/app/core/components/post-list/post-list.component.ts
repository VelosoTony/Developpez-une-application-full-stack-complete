import { Observable, Subscription, map } from 'rxjs';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Post } from 'src/app/core/interfaces/post.interface';
import { UserService } from '../../services/user.service';
import { PostService } from '../../services/post.service';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.scss'],
})
export class PostListComponent implements OnInit, OnDestroy {
  public posts$!: Observable<Post[]>;
  private postSub!: Subscription;
  public breakpoint!: number;
  public sortAsc: boolean = true;

  constructor(
    private postService: PostService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.fetchPosts();
    this.breakpoint = window.innerWidth <= 768 ? 1 : 2;
  }

  ngOnDestroy(): void {
    if (this.postSub !== undefined) {
      this.postSub.unsubscribe();
    }
  }
  onResize(event: Event): void {
    this.breakpoint = window.innerWidth <= 768 ? 1 : 2;
  }

  public fetchPosts() {
    this.posts$ = this.postService.getAllPosts().pipe(
      map(posts => {
        return posts.sort((a, b) => {
          const dateA = a.createdDate ? new Date(a.createdDate) : new Date(0);
          const dateB = b.createdDate ? new Date(b.createdDate) : new Date(0);

          if (this.sortAsc) {
            return dateA.getTime() - dateB.getTime();
          } else {
            return dateB.getTime() - dateA.getTime();
          }
        });
      })
    );
  }

  public toggleSwitch() {
    this.sortAsc = !this.sortAsc;
    this.fetchPosts();
  }

}

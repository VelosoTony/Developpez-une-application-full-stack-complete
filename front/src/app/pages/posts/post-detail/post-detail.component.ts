import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { Post } from 'src/app/core/interfaces/post.interface';
import { PostService } from 'src/app/core/services/post.service';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.scss'],
})
export class PostDetailComponent implements OnInit, OnDestroy {
  public post!: Post;
  public post$!: Observable<Post>;
  private postSub!: Subscription;
  public onError = false;

  constructor(
    private postService: PostService,
    private route: ActivatedRoute
  ) {}

  public ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id')!;
    this.post$ = this.postService.getPostById(id);
    this.postSub = this.post$.subscribe({
      next: (post: Post) => {
        this.post = post;
      },
      error: (_error: any) => (this.onError = true),
    });
  }

  ngOnDestroy(): void {
    if (this.postSub !== undefined) {
      this.postSub.unsubscribe();
    }
  }
}

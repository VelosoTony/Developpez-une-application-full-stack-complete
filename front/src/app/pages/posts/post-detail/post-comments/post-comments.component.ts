import { FormBuilder, Validators } from '@angular/forms';
import { ChangeDetectorRef, Component, OnInit, OnDestroy } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { Comment } from 'src/app/core/interfaces/comment.interface';
import { PostService } from 'src/app/core/services/post.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommentCreateRequest } from 'src/app/core/interfaces/request/commentCreateRequest.interface';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-post-comments',
  templateUrl: './post-comments.component.html',
  styleUrls: ['./post-comments.component.scss'],
})
export class PostCommentsComponent implements OnInit, OnDestroy {
  public onError = false;
  public comments$!: Observable<Comment[]>;
  public postId!: string;
  private createCommentSub!: Subscription;

  public form = this.fb.group({
    content: [
      '',
      [Validators.required, Validators.minLength(5), Validators.maxLength(255)],
    ],
  });

  constructor(
    private cdr: ChangeDetectorRef,
    private fb: FormBuilder,
    private postService: PostService,
    private route: ActivatedRoute,
    private router: Router,
    private matSnackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.postId = this.route.snapshot.paramMap.get('id')!;
    this.comments$ = this.postService.getComments(this.postId);
  }

  ngOnDestroy(): void {
    if (this.createCommentSub !== undefined) {
      this.createCommentSub.unsubscribe();
    }
  }
  /**
   * Submit method handles the comment form submlssion.
   */
  public submit(): void {
    if (this.form.valid) {
      const commentCreateRequest = this.form.value as CommentCreateRequest;

      this.createCommentSub = this.postService
        .createComment(this.postId, commentCreateRequest)
        .subscribe({
          next: (resp) => {
            this.infoTip('Commentaire ajouté !');
            this.form.setValue({ content: '' });
            this.ngOnInit();
          },
          error: (_error: any) => (this.onError = true),
        });
    }
  }

  private infoTip(message: string): void {
    this.matSnackBar.open(message, 'Close', { duration: 3000 });
  }
}

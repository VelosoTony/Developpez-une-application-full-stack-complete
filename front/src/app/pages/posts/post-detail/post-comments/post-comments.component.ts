import { FormBuilder, Validators } from '@angular/forms';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
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
export class PostCommentsComponent implements OnInit {
  public onError = false;
  public comments$!: Observable<Comment[]>;
  public postId!: string;

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
    console.log('ID ' + this.postId);
    this.comments$ = this.postService.getComments(this.postId);
  }

  public submit(): void {
    if (this.form.valid) {
      const commentCreateRequest = this.form.value as CommentCreateRequest;
      console.log(commentCreateRequest);
      this.postService
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

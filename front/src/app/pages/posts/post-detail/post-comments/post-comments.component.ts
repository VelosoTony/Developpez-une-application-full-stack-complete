import { FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Comment } from 'src/app/core/interfaces/comment.interface';
import { PostService } from 'src/app/core/services/post.service';
import { ActivatedRoute } from '@angular/router';
import { CommentCreateRequest } from 'src/app/core/interfaces/request/commentCreateRequest.interface';

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
      [
        Validators.required,
        Validators.minLength(10),
        Validators.maxLength(255),
      ],
    ],
  });

  constructor(
    private fb: FormBuilder,
    private postService: PostService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.postId = this.route.snapshot.paramMap.get('id')!;
    console.log('ID ' + this.postId);
    this.comments$ = this.postService.getComments(this.postId);
  }

  public submit(): void {
    const commentCreateRequest = this.form.value as CommentCreateRequest;
    console.log(commentCreateRequest);
    this.postService
      .createComment(this.postId, commentCreateRequest)
      .subscribe({
        next: (_: any) => {
          this.ngOnInit();
          this.form.reset();
        },
        error: (_error: any) => (this.onError = true),
      });
  }
}

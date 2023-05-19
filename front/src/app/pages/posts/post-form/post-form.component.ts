import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { PostCreateRequest } from 'src/app/core/interfaces/request/postCreateRequest.interface';
import { Topic } from 'src/app/core/interfaces/topic.interface';
import { PostService } from 'src/app/core/services/post.service';
import { TopicService } from 'src/app/core/services/topic.service';

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.scss'],
})
export class PostFormComponent {
  public onError = false;
  public topics$!: Observable<Topic[]>;

  public form = this.fb.group({
    topicId: ['', [Validators.required]],
    title: ['', [Validators.required], Validators.maxLength(255)],
    content: ['', [Validators.required]],
  });

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private topicService: TopicService,
    private postService: PostService
  ) {
    this.topics$ = this.topicService.all();
  }

  public submit(): void {
    const postCreateRequest = this.form.value as PostCreateRequest;
    console.log(postCreateRequest);
    this.postService.createPost(postCreateRequest).subscribe({
      next: (_) => {
        this.router.navigate(['/posts']);
      },
      error: (_error: any) => (this.onError = true),
    });
  }
}

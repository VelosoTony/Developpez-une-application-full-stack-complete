import { PostCreateRequest } from 'src/app/core/interfaces/request/postCreateRequest.interface';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from '../interfaces/post.interface';

@Injectable({
  providedIn: 'root',
})
export class PostService {
  private pathService = 'api/posts';

  constructor(private httpClient: HttpClient) {}

  public getAllPosts(): Observable<Post[]> {
    return this.httpClient.get<Post[]>(this.pathService);
  }

  public getPostById(id: string): Observable<Post> {
    console.log(`${this.pathService}/${id}`);
    return this.httpClient.get<Post>(`${this.pathService}/${id}`);
  }

  public createPost(postRequest: PostCreateRequest): Observable<void> {
    const body = {
      id: postRequest.topicId,
      title: postRequest.title,
      content: postRequest.content,
    };
    return this.httpClient.post<void>(`${this.pathService}`, body);
  }
}

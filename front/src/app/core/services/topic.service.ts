import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Topic } from '../interfaces/topic.interface';

@Injectable({
  providedIn: 'root',
})
export class TopicService {
  // private pathService = './assets/mock/topics.json';
  private pathService = 'api/topics';

  constructor(private httpClient: HttpClient) {}

  public all(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(this.pathService);
  }
}

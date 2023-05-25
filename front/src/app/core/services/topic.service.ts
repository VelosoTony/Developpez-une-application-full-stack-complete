import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Topic } from '../interfaces/topic.interface';

@Injectable({
  providedIn: 'root',
})
export class TopicService {
  private pathService = 'api/topics';

  constructor(private httpClient: HttpClient) {}

  public all(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(this.pathService);
  }

  public unsubscribedTopics(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(`${this.pathService}/unsubscribed`);
  }

  public subscribeTopic(id: string): Observable<void> {
    return this.httpClient.post<void>(
      `${this.pathService}/${id}/subscribe`,
      null
    );
  }

  public unsubscribeTopic(id: string): Observable<void> {
    return this.httpClient.delete<void>(
      `${this.pathService}/${id}/unsubscribe`
    );
  }
}

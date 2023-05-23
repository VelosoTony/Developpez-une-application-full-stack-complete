import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../interfaces/user.interface';
import { Topic } from '../interfaces/topic.interface';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private pathService = 'api/user';

  constructor(private httpClient: HttpClient) {}

  public getUser(): Observable<User> {
    return this.httpClient.get<User>(this.pathService);
  }

  public getUserSubscription(): Observable<Topic[]> {
    return this.httpClient.get<Topic[]>(this.pathService + '/subscription');
  }

  public updateUser(username: string, email: string): Observable<User> {
    const body = { username: username, email: email };
    return this.httpClient.put<User>(this.pathService, body);
  }

  public updatePassword(password: string): Observable<User> {
    const body = { password: password };
    console.log('User.service updatePassword ' + body);
    return this.httpClient.put<User>(this.pathService + '/password', body);
  }
}

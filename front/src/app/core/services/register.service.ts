import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegisterRequest } from '../interfaces/request/registerRequest.interface';

@Injectable({
  providedIn: 'root',
})
export class RegisterService {
  private pathService = 'api/auth';

  constructor(private httpClient: HttpClient) {}

  public register(registerRequest: RegisterRequest): Observable<void> {
    return this.httpClient.post<void>(
      `${this.pathService}/register`,
      registerRequest
    );
  }
}

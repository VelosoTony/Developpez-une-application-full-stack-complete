import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginRequest } from '../interfaces/request/loginRequest.interface';
import { LoginResponse } from '../interfaces/response/loginResponse.interface';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private pathService = 'api/auth';

  constructor(private httpClient: HttpClient) {}

  public login(loginRequest: LoginRequest): Observable<LoginResponse> {
    console.log('LOGIN ' + loginRequest);
    return this.httpClient.post<LoginResponse>(
      `${this.pathService}/login`,
      loginRequest
    );
  }
}

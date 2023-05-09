import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { SessionInformation } from '../interfaces/sessionInformation.interface';
import { JwtHelperService } from '@auth0/angular-jwt';
import { LoginResponse } from '../interfaces/response/loginResponse.interface';

export const JWT_NAME = 'jwt_token';
@Injectable({
  providedIn: 'root',
})
export class SessionService {
  constructor(private jwtHelper: JwtHelperService) {}

  private isLoggedSubject = new BehaviorSubject<boolean>(false);

  public $isLogged(): Observable<boolean> {
    return this.isLoggedSubject.asObservable();
  }
  
  public getToken() {
    return localStorage.getItem(JWT_NAME);
  }

  public isValid(): boolean {
    const token = this.getToken();
    return !this.jwtHelper.isTokenExpired(token);
  }

  public logIn(JWT: LoginResponse): void {
    localStorage.setItem(JWT_NAME, JWT.token);
    this.isLoggedSubject.next(true);
  }

  public logOut(): void {
    localStorage.clear();
    this.isLoggedSubject.next(false);
  }
}

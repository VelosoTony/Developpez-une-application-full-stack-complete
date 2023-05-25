import { LoginService } from './../../core/services/login.service';
import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from 'src/app/core/interfaces/request/loginRequest.interface';
import { LoginResponse } from 'src/app/core/interfaces/response/loginResponse.interface';
import { SessionService } from 'src/app/core/services/session.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  public hide = true;
  public onError = false;

  public form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.min(3)]],
  });

  constructor(
    private loginService: LoginService,
    private fb: FormBuilder,
    private router: Router,
    private sessionService: SessionService
  ) {}

  public submit(): void {
    const loginRequest = this.form.value as LoginRequest;
    this.loginService.login(loginRequest).subscribe({
      next: (response: LoginResponse) => {
        // if login successed, the token is returned,
        // then called the sessionService.logIn to store the token in LocalStorage
        this.sessionService.logIn(response);

        this.router.navigate(['/posts']);
      },
      error: (_error: any) => (this.onError = true),
    });
  }
}

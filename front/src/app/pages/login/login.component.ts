import { LoginService } from './../../core/services/login.service';
import { Component, OnDestroy } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { LoginRequest } from 'src/app/core/interfaces/request/loginRequest.interface';
import { LoginResponse } from 'src/app/core/interfaces/response/loginResponse.interface';
import { SessionService } from 'src/app/core/services/session.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnDestroy {
  public hide = true;
  public onError = false;
  private loginSub!: Subscription;

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

  ngOnDestroy(): void {
    if (this.loginSub !== undefined) {
      this.loginSub.unsubscribe();
    }
  }

  public submit(): void {
    const loginRequest = this.form.value as LoginRequest;
    this.loginSub = this.loginService.login(loginRequest).subscribe({
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

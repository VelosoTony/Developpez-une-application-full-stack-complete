import { LoginService } from './../../core/services/login.service';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from 'src/app/core/interfaces/request/loginRequest.interface';
import { LoginResponse } from 'src/app/core/interfaces/response/loginResponse.interface';
import { SessionService } from 'src/app/core/services/session.service';
import { emailValidator } from 'src/app/core/validators/email.validator';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
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

  ngOnInit(): void {}

  public submit(): void {
    const loginRequest = this.form.value as LoginRequest;
    this.loginService.login(loginRequest).subscribe({
      next: (response: LoginResponse) => {
        this.sessionService.logIn(response);
        this.router.navigate(['/topics']);
      },
      error: (_error: any) => (this.onError = true),
    });
  }
}

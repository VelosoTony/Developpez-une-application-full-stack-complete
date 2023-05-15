import { LoginService } from './../../core/services/login.service';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from 'src/app/core/interfaces/request/loginRequest.interface';
import { RegisterRequest } from 'src/app/core/interfaces/request/registerRequest.interface';
import { LoginResponse } from 'src/app/core/interfaces/response/loginResponse.interface';
import { RegisterService } from 'src/app/core/services/register.service';
import { SessionService } from 'src/app/core/services/session.service';
import { emailValidator } from 'src/app/core/validators/email.validator';
import { passwordValidator } from 'src/app/core/validators/password.validator';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
  public hide = true;
  public onError = false;

  public form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    username: ['', [Validators.required]],
    password: ['', [Validators.required, passwordValidator()]],
  });

  constructor(
    private registerService: RegisterService,
    private fb: FormBuilder,
    private router: Router,
    private sessionService: SessionService
  ) {}

  ngOnInit(): void {}

  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    this.registerService.register(registerRequest).subscribe({
      next: (_: void) => this.router.navigate(['/login']),
      error: (_) => (this.onError = true),
    });
  }
}

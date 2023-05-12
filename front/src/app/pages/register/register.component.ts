import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterRequest } from 'src/app/core/interfaces/request/registerRequest.interface';
import { RegisterService } from 'src/app/core/services/register.service';
import { SessionService } from 'src/app/core/services/session.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  public hide = true;
  public onError = false;

  public form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.min(3)]],
  });

  constructor(
    private registerService: RegisterService,
    private fb: FormBuilder,
    private router: Router,
    private sessionService: SessionService
  ) {}

  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    this.registerService.register(registerRequest).subscribe({
      next: (_: void) => this.router.navigate(['/login']),
      error: (_) => (this.onError = true),
    });
  }
}

import { Component, OnDestroy } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { RegisterRequest } from 'src/app/core/interfaces/request/registerRequest.interface';
import { RegisterService } from 'src/app/core/services/register.service';
import { SessionService } from 'src/app/core/services/session.service';
import { passwordValidator } from 'src/app/core/validators/password.validator';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnDestroy {
  public onError = false;
  private registerSub!: Subscription;

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

  ngOnDestroy(): void {
    if (this.registerSub !== undefined) {
      this.registerSub.unsubscribe();
    }
  }

  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    this.registerSub = this.registerService
      .register(registerRequest)
      .subscribe({
        next: (_: void) => this.router.navigate(['/login']),
        error: (_) => (this.onError = true),
      });
  }
}

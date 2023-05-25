import { UserService } from './../../core/services/user.service';
import { SessionService } from 'src/app/core/services/session.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/core/interfaces/user.interface';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { passwordValidator } from 'src/app/core/validators/password.validator';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit, OnDestroy {
  public currentEmail!: string;
  public hide = true;
  public user!: User;
  private userSub!: Subscription;
  private userUpdateSub!: Subscription;
  private passUpdateSub!: Subscription;

  public userForm = this.fb.group({
    username: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
  });

  public passwordForm = this.fb.group(
    {
      newPassword: ['', [Validators.required, passwordValidator()]],
      confirmPassword: ['', [Validators.required]],
    },
    {
      validator: this.passwordMatchValidator,
    }
  );

  constructor(
    private router: Router,
    private userService: UserService,
    private sessionService: SessionService,
    private fb: FormBuilder,
    private matSnackBar: MatSnackBar
  ) {}

  /**
   * Custom validator function to validate that the new password
   * and confirm password fields match each other.
   */
  private passwordMatchValidator(control: AbstractControl) {
    const newPasswordControl = control.get('newPassword');
    const confirmPasswordControl = control.get('confirmPassword');

    if (!newPasswordControl || !confirmPasswordControl) {
      return null;
    }

    const newPassword = newPasswordControl.value;
    const confirmPassword = confirmPasswordControl.value;

    if (newPassword !== confirmPassword) {
      confirmPasswordControl.setErrors({ passwordMismatch: true });
      return { passwordMismatch: true };
    } else {
      confirmPasswordControl.setErrors(null);
      return null;
    }
  }

  ngOnInit(): void {
    // Retrieve user information
    this.userSub = this.userService.getUser().subscribe({
      next: (user: User) => {
        this.user = user;
        this.userForm.setValue({
          username: user.username,
          email: user.email,
        });
        this.currentEmail = user.email;
        this.passwordForm.setValue({
          newPassword: 'password',
          confirmPassword: 'password',
        });
      },
    });
  }

  ngOnDestroy(): void {
    this.userSub.unsubscribe();
    if (this.userUpdateSub !== undefined) {
      this.userUpdateSub.unsubscribe();
    }

    if (this.passUpdateSub !== undefined) {
      this.passUpdateSub.unsubscribe();
    }
  }

  public logout() {
    this.sessionService.logOut();
    this.router.navigateByUrl('/login');
  }

  /**
   * Handle update of username and email update
   */
  public onSubmit() {
    const username = this.userForm.value.username!;
    const email = this.userForm.value.email!;
    if (username !== undefined && email !== undefined) {
      this.userUpdateSub = this.userService
        .updateUser(username, email)
        .subscribe({
          next: (updateUser: User) => {
            this.user = updateUser;
            this.infoTip('Informations mise à jour!');
            if (this.currentEmail !== email) {
              this.infoTip('Email modifié, vous devez vous reconnecter!');
              this.logout();
            }
          },
          error: (_: any) => null,
        });
    }
  }

  /**
   * Handle update of user password
   */
  public onSubmitPassword() {
    const pass = this.passwordForm.value.newPassword!;
    console.log('Update password [' + pass + ']');
    if (pass !== undefined) {
      this.passUpdateSub = this.userService.updatePassword(pass).subscribe({
        next: (updateUser: User) => {
          this.user = updateUser;
          this.infoTip('Mot de passe modifié!');
        },
        error: (_: any) => null,
      });
    }
  }
  private infoTip(message: string): void {
    this.matSnackBar.open(message, 'Close', { duration: 3000 });
  }
}

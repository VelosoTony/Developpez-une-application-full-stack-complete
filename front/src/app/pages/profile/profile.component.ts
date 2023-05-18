import { UserService } from './../../core/services/user.service';
import { SessionService } from 'src/app/core/services/session.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/core/interfaces/user.interface';
import { FormBuilder, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit, OnDestroy {
  public user!: User;
  private userSub!: Subscription;
  private userUpdateSub!: Subscription;
  public userForm = this.fb.group({
    username: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
  });

  constructor(
    private router: Router,
    private userService: UserService,
    private sessionService: SessionService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.userSub = this.userService.getUser().subscribe({
      next: (response: User) => {
        this.user = response;
        this.userForm.setValue({
          username: response.username,
          email: response.email,
        });
      },
    });
  }

  ngOnDestroy(): void {
    this.userSub.unsubscribe();
    if (this.userUpdateSub !== undefined) {
      this.userUpdateSub.unsubscribe();
    }
  }

  public logout() {
    this.sessionService.logOut();
    this.router.navigateByUrl('/');
  }

  public onSubmit() {
    const username = this.userForm.value.username!;
    const email = this.userForm.value.email!;
    if (username !== undefined && email !== undefined) {
      this.userUpdateSub = this.userService
        .updateUser(username, email)
        .subscribe({
          next: (response: User) => {
            this.user = response;
            this.ngOnInit();
          },
          error: (_: any) => null,
        });
    }
  }
}

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { TopicsComponent } from './pages/topics/topics.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { PostsComponent } from './pages/posts/posts.component';
import { AuthGuard } from './core/guards/auth.guard';
import { ProfileComponent } from './pages/profile/profile.component';
import { PostFormComponent } from './pages/posts/post-form/post-form.component';
import { PostDetailComponent } from './pages/posts/post-detail/post-detail.component';

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
  },
  {
    path: 'topics',
    canActivate: [AuthGuard],
    component: TopicsComponent,
  },
  {
    path: 'posts',
    canActivate: [AuthGuard],
    component: PostsComponent,
  },
  {
    path: 'post-form',
    canActivate: [AuthGuard],
    component: PostFormComponent,
  },
  {
    path: 'post-detail/:id',
    canActivate: [AuthGuard],
    component: PostDetailComponent,
  },
  {
    path: 'profile',
    canActivate: [AuthGuard],
    component: ProfileComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

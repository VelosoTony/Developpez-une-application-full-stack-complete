import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { MatCardModule } from '@angular/material/card';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { JwtInterceptor } from './core/interceptors/jwt.interceptor';
import { RegisterComponent } from './pages/register/register.component';
import { LoginComponent } from './pages/login/login.component';
import { TopicListComponent } from './core/components/topic-list/topic-list.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { HeaderComponent } from './core/components/header/header.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSelectModule } from '@angular/material/select';
import { JwtHelperService } from '@auth0/angular-jwt';
import { PostsComponent } from './pages/posts/posts.component';
import { TopicListItemComponent } from './core/components/topic-list-item/topic-list-item.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { TopicsComponent } from './pages/topics/topics.component';
import { PostListComponent } from './core/components/post-list/post-list.component';
import { PostListItemComponent } from './core/components/post-list-item/post-list-item.component';
import { PostFormComponent } from './pages/posts/post-form/post-form.component';
import { PostDetailComponent } from './pages/posts/post-detail/post-detail.component';
import { PostCommentsComponent } from './pages/posts/post-detail/post-comments/post-comments.component';
import { ShortenPipe } from './core/pipes/shorten.pipe';
import { TimeAgoPipe } from './core/pipes/time-ago';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import {MatMenuModule} from '@angular/material/menu';

const materialModule = [
  MatButtonModule,
  MatCardModule,
  MatIconModule,
  MatGridListModule,
  MatFormFieldModule,
  MatInputModule,
  MatSidenavModule,
  MatSnackBarModule,
  MatToolbarModule,
  MatSelectModule,
  MatMenuModule,
];
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RegisterComponent,
    LoginComponent,
    TopicListComponent,
    HeaderComponent,
    PostsComponent,
    TopicListItemComponent,
    ProfileComponent,
    TopicsComponent,
    PostListComponent,
    PostListItemComponent,
    PostFormComponent,
    PostDetailComponent,
    PostCommentsComponent,
    ShortenPipe,
    TimeAgoPipe,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    ...materialModule,
  ],
  providers: [
    { provide: JwtHelperService, useFactory: () => new JwtHelperService() },
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}

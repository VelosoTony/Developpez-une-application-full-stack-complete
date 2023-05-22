import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { SessionService } from '../../services/session.service';
import { Observable } from 'rxjs/internal/Observable';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent {
  public isLogged$: Observable<boolean> = this.sessionService.$isLogged();
  // get page from app-component in order to modify link active
  @Input() page!: string;

  constructor(private router: Router, private sessionService: SessionService) {
    // this.page = this.router.url;
    //console.log('ActiveRoute : ' + this.page);
  }

  public navToProfile(): void {
    this.router.navigateByUrl('profile');
  }
  public navToPost(): void {
    this.router.navigateByUrl('posts');
  }

  public navToTopic(): void {
    this.router.navigateByUrl('topics');
  }
}

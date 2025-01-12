import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from '../../interfaces/post.interface';

@Component({
  selector: 'app-post-list-item',
  templateUrl: './post-list-item.component.html',
  styleUrls: ['./post-list-item.component.scss'],
})
export class PostListItemComponent {
  @Input() post!: Post;
  public posts!: Post[];
  @Input() onDetail!: boolean;

  constructor(private router: Router, private route: ActivatedRoute) {}

  public showDetail(id: number): void {
    this.router.navigateByUrl('/post-detail/' + id);
  }
}

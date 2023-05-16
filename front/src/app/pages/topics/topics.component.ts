import { SessionService } from './../../core/services/session.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Observable, filter, map } from 'rxjs';
import { Topic } from 'src/app/core/interfaces/topic.interface';
import { TopicService } from 'src/app/core/services/topic.service';

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.scss'],
})
export class TopicsComponent {
  public topics$!: Observable<Topic[]>;

  constructor(private topicService: TopicService) {
    this.topics$ = this.topicService.all();
  }
}

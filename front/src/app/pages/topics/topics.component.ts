import { SessionService } from 'src/app/core/services/session.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Topic } from 'src/app/core/interfaces/topic.interface';
import { TopicService } from 'src/app/core/services/topic.service';

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.scss'],
})
export class TopicsComponent implements OnInit {
  public topics$: Observable<Topic[]> = this.topicService.all();

  constructor(private topicService: TopicService) {}

  ngOnInit(): void {}

  public followTopic(): void {}

  public unfollowTopic(): void {}
}

import { UserService } from 'src/app/core/services/user.service';
import { Component, Input, OnInit, OnDestroy } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { Topic } from 'src/app/core/interfaces/topic.interface';
import { TopicService } from 'src/app/core/services/topic.service';

@Component({
  selector: 'app-topic-list',
  templateUrl: './topic-list.component.html',
  styleUrls: ['./topic-list.component.scss'],
})
export class TopicListComponent implements OnInit, OnDestroy {
  public topics!: Topic[];
  public topics$!: Observable<Topic[]>;
  private topicSub!: Subscription;

  @Input() onProfile!: boolean;

  constructor(
    private topicService: TopicService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.fetchTopics();
  }

  ngOnDestroy(): void {
    if (this.topicSub !== undefined) {
      this.topicSub.unsubscribe();
    }
  }

  public fetchTopics() {
    console.log('fetchTopics');
    if (this.onProfile) {
      this.topics$ = this.userService.getUserSubscription();
    } else {
      this.topics$ = this.topicService.all();
    }

    this.topicSub = this.topics$.subscribe(
      (topics: Topic[]) => (this.topics = topics)
    );
  }

  public onTopicUnsubscribed(topic_id: string) {
    console.log('Delete ' + topic_id);
    this.topicService
      .unsubscribe(topic_id)
      .subscribe((_) => this.fetchTopics());
  }

  public onTopicSubscribed(topic_id: string) {
    console.log('Add ' + topic_id);
    this.topicService.subscribe(topic_id).subscribe((_) => this.fetchTopics());
  }
}

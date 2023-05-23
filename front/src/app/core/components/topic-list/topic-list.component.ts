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
  public breakpoint!: number;

  @Input() onProfile!: boolean;

  constructor(
    private topicService: TopicService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.fetchTopics();
    this.breakpoint = window.innerWidth <= 768 ? 1 : 2;
  }

  ngOnDestroy(): void {
    if (this.topicSub !== undefined) {
      this.topicSub.unsubscribe();
    }
  }
  onResize(event: Event): void {
    this.breakpoint = window.innerWidth <= 768 ? 1 : 2;
  }

  public fetchTopics() {
    console.log('fetchTopics');
    if (this.onProfile) {
      this.topics$ = this.userService.getUserSubscription();
    } else {
      this.topics$ = this.topicService.unsubscribedTopics();
    }

    this.topicSub = this.topics$.subscribe(
      (topics: Topic[]) => (this.topics = topics)
    );
  }

  public onTopicUnsubscribed(topic_id: string) {
    console.log('Delete ' + topic_id);
    this.topicService
      .unsubscribeTopic(topic_id)
      .subscribe((_) => this.fetchTopics());
  }

  public onTopicSubscribed(topic_id: string) {
    console.log('Add ' + topic_id);
    this.topicService
      .subscribeTopic(topic_id)
      .subscribe((_) => this.fetchTopics());
  }
}

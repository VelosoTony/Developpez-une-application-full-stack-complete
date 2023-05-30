import { UserService } from 'src/app/core/services/user.service';
import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { Topic } from 'src/app/core/interfaces/topic.interface';
import { TopicService } from 'src/app/core/services/topic.service';

@Component({
  selector: 'app-topic-list',
  templateUrl: './topic-list.component.html',
  styleUrls: ['./topic-list.component.scss'],
})
export class TopicListComponent implements OnInit, OnDestroy {
  public topics$!: Observable<Topic[]>;
  public gridColumnNumber!: number;
  private unsubscribeTopicSub!: Subscription;
  private subscribeTopicSub!: Subscription;

  @Input() onProfile!: boolean;

  constructor(
    private topicService: TopicService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    // Fetch the topics
    this.fetchTopics();

    // Set the grid number of column
    this.setGridColumNumber();
  }

  ngOnDestroy(): void {
    if (this.unsubscribeTopicSub !== undefined) {
      this.unsubscribeTopicSub.unsubscribe();
    }

    if (this.subscribeTopicSub !== undefined) {
      this.subscribeTopicSub.unsubscribe();
    }
  }

  /**
   * Sets the number of grid columns based on the window width.
   */
  public setGridColumNumber() {
    this.gridColumnNumber = window.innerWidth <= 768 ? 1 : 2;
  }

  onResize(event: Event): void {
    // Set the grid number of column
    this.setGridColumNumber();
  }

  /**
   * Fetches topics based on the context.
   */
  public fetchTopics() {
    if (this.onProfile) {
      // Fetch topics for user subscription when on profile page
      this.topics$ = this.userService.getUserSubscription();
    } else {
      // Fetch unsubscribed topics when not on profile page
      this.topics$ = this.topicService.unsubscribedTopics();
    }
  }

  /**
   * Handles the event when a topic is unsubscribed.
   * @param topic_id The ID of the topic to unsubscribe.
   */
  public onTopicUnsubscribed(topic_id: string) {
    this.unsubscribeTopicSub = this.topicService
      .unsubscribeTopic(topic_id)
      .subscribe((_) => this.fetchTopics());
  }

  /**
   * Handles the event when a topic is subscribed.
   * @param topic_id The ID of the topic to subscribe.
   */
  public onTopicSubscribed(topic_id: string) {
    this.subscribeTopicSub = this.topicService
      .subscribeTopic(topic_id)
      .subscribe((_) => this.fetchTopics());
  }
}

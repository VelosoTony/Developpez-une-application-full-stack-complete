import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Topic } from '../../interfaces/topic.interface';

@Component({
  selector: 'app-topic-list-item',
  templateUrl: './topic-list-item.component.html',
  styleUrls: ['./topic-list-item.component.scss'],
})
export class TopicListItemComponent {
  @Input() topic!: Topic;
  @Input() onProfile!: boolean;

  @Output() topicUnsubscribed = new EventEmitter<string>();

  @Output() topicSubscribed = new EventEmitter<string>();

  public subscribe(topic_id: string): void {
    console.log('EventSubscribe Topic [' + topic_id + ']');
    this.topicSubscribed.emit(topic_id);
  }

  public unsubscribe(topic_id: string): void {
    console.log('EventUnsubscribe Topic [' + topic_id + ']');
    this.topicUnsubscribed.emit(topic_id);
  }
}

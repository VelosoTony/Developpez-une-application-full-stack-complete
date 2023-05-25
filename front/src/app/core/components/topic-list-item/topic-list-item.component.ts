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

  /**
   * Subscribes to a topic and emits the `topicSubscribed` event.
   * @param topic_id The ID of the topic to subscribe.
   */
  public subscribe(topic_id: string): void {
    console.log('EventSubscribe Topic [' + topic_id + ']');
    this.topicSubscribed.emit(topic_id);
  }

  /**
   * Unsubscribes from a topic and emits the `topicUnsubscribed` event.
   * @param topic_id The ID of the topic to unsubscribe.
   */
  public unsubscribe(topic_id: string): void {
    console.log('EventUnsubscribe Topic [' + topic_id + ']');
    this.topicUnsubscribed.emit(topic_id);
  }
}

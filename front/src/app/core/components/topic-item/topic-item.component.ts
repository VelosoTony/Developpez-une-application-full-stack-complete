import { Component, Input, OnInit } from '@angular/core';
import { Topic } from '../../interfaces/topic.interface';
import { TopicService } from '../../services/topic.service';

@Component({
  selector: 'app-topic-item',
  templateUrl: './topic-item.component.html',
  styleUrls: ['./topic-item.component.scss'],
})
export class TopicItemComponent implements OnInit {
  @Input() topic!: Topic;

  public buttonText!: string;

  constructor(private topicService: TopicService) {}

  ngOnInit() {
    this.buttonText = "S'abonner";
  }
  public subscribe(topic_id: string): void {
    console.log('Topic [' + topic_id + ']');
    this.topicService.subscribe(topic_id).subscribe();
    this.buttonText = 'Abonné';
  }

  public unsubscribe(topic_id: string): void {
    console.log('Topic [' + topic_id + ']');
    this.topicService.unsubscribe(topic_id).subscribe();
  }
}

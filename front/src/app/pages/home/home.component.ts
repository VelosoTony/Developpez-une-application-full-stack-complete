import { ObserversModule } from '@angular/cdk/observers';
import { Component, OnInit } from '@angular/core';
import { Subscription, Observable } from 'rxjs';
import { Topic } from 'src/app/core/interfaces/topic.interface';
import { TopicService } from 'src/app/core/services/topic.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  public topics$: Observable<Topic[]> = this.topicService.all();

  constructor(private topicService: TopicService) {}

  ngOnInit(): void {}

  start() {
    alert('Commencez par lire le README et à vous de jouer !');
  }
}
